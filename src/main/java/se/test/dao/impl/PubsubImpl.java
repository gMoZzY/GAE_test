package se.test.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.ListTopicsResponse;
import com.google.api.services.pubsub.model.PublishRequest;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.api.services.pubsub.model.PushConfig;
import com.google.api.services.pubsub.model.Subscription;
import com.google.api.services.pubsub.model.Topic;

import com.google.common.collect.ImmutableList;

import se.test.pojo.PubsubPojo;



public class PubsubImpl implements se.test.dao.Pubsub {

	Pubsub pubsub;
	
	public PubsubImpl()
	{
		
		this.pubsub = new Pubsub.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), null)
				.setRootUrl("http://localhost:6666").build();			
		//pubsub = PubSubOptions.newBuilder().setHost("localhost:6666").build().getService();
	}
	
	@Override
	public PubsubPojo setTopic(PubsubPojo pubsubPojo) 
	{
		
		String fullName = pubsubPojo.getTopicName();
		try
		{
            return new PubsubPojo(this.pubsub.projects().topics().get(fullName).execute());
        } catch(IOException e) {
                try 
                {
                	return new PubsubPojo(this.pubsub.projects().topics().create(fullName, new Topic()).execute());
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
        }
		
		/*
		 * TopicInfo topicInfo = TopicInfo.of(name);
		 * return this.pubsub.create(topicInfo);
		 */
		return null;
	}

	@Override
	public List<PubsubPojo> getTopics(String projectId) 
	{
		List<PubsubPojo> topicList = new ArrayList<PubsubPojo>();
		try
		{
			String nextPageToken = null;
	        Pubsub.Projects.Topics.List listMethod = this.pubsub.projects().topics().list("projects/" + projectId);
	        do {
	            if (nextPageToken != null) 
	            {
	                listMethod.setPageToken(nextPageToken);
	            }
	            ListTopicsResponse response = listMethod.execute();
	            if (!response.isEmpty()) 
	            {
	                for (Topic tmp : response.getTopics()) 
	                {
	                    topicList.add(new PubsubPojo(tmp));
	                }
	            }
	            nextPageToken = response.getNextPageToken();
	        } while (nextPageToken != null);
	        return topicList;
		}catch(IOException e){

		}
/*
		Page<Topic> topics = this.pubsub.listTopics(ListOption.pageSize(100));
		Iterator<Topic> topicIterator = topics.iterateAll();
	    List<Topic> topicList = new ArrayList<Topic>();
		while (topicIterator.hasNext()) {
	      topicList.add(topicIterator.next());
	    }
*/
		return topicList;
	}
	
	@Override
	public PubsubPojo getSubscription(String projectId) 
	{
	        String message = "TEST";
	        if (!"".equals(message)) {
	        	try
	        	{
		            String fullTopicName = String.format("projects/%s/topics/%s",
		                    "GAE_test",
		                    "HEJ");
		            PubsubMessage pubsubMessage = new PubsubMessage();
		            pubsubMessage.encodeData(message.getBytes("UTF-8"));
		            PublishRequest publishRequest = new PublishRequest();
		            publishRequest.setMessages(ImmutableList.of(pubsubMessage));
	
		            this.pubsub.projects().topics().publish(fullTopicName, publishRequest).execute();
	        	} catch(IOException e){}
	        }

		return null;
	}
	
	@Override
	public PubsubPojo setSubscription(PubsubPojo pubsubPojo) {
		
		String fullName = pubsubPojo.getSubscriptionName();

        try {
            this.pubsub.projects().subscriptions().get(fullName).execute();
        } catch (IOException e) {
                try
                {
	        		String fullTopicName = pubsubPojo.getTopicName();
	                PushConfig pushConfig = new PushConfig().setPushEndpoint("http://localhost:8080");
	                Subscription subscription = new Subscription().setTopic(fullTopicName).setPushConfig(pushConfig);
	                return new PubsubPojo(this.pubsub.projects().subscriptions().create(fullName, subscription).execute());
                } catch(IOException e1) {}
        }
		return null;
	}
	
}