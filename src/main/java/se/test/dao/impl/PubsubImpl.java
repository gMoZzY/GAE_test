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
import se.test.util.Util;



public class PubsubImpl implements se.test.dao.Pubsub {

	Pubsub pubsub;

	public PubsubImpl()
	{
		this.pubsub = new Pubsub.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), null).setRootUrl(Util.PUBSUB_ROOT_URL).build();			
	}
	
	@Override
	public PubsubPojo setTopic(PubsubPojo pubsub) 
	{
		
		String fullName = pubsub.getTopicName();
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
		}catch(IOException e){}
		
		return topicList;
	}
	
	@Override
	public void deleteTopic(PubsubPojo pubsub) 
	{
		try {
			this.pubsub.projects().topics().delete(pubsub.getTopicName());
		} catch (IOException e) {}
	}
	
	
	
	@Override
	public PubsubPojo getSubscription(String projectId) 
	{
		return null;
	}

	@Override
	public PubsubPojo setSubscription(PubsubPojo pubsub) {
		
		String fullName = pubsub.getSubscriptionName();

        try {
            this.pubsub.projects().subscriptions().get(fullName).execute();
        } catch (IOException e) {
                try
                {
	        		String fullTopicName = pubsub.getTopicName();
	                PushConfig pushConfig = new PushConfig().setPushEndpoint(pubsub.getPushEndpoint());
	                Subscription subscription = new Subscription().setTopic(fullTopicName).setPushConfig(pushConfig);
	                return new PubsubPojo(this.pubsub.projects().subscriptions().create(fullName, subscription).execute());
                } catch(IOException e1) {}
        }
		return null;
	}
	
	@Override
	public void deleteSubscription(PubsubPojo pubsub) 
	{
		try {
			this.pubsub.projects().subscriptions().delete(pubsub.getSubscriptionName());
		} catch (IOException e) {}
	}
	
	@Override
	public void sendMessage(PubsubPojo pubsub) {
        if (pubsub.getMessage() != null && !pubsub.getMessage().isEmpty()) 
        {
        	try
        	{
	            String fullTopicName = pubsub.getTopicName();
	            PubsubMessage pubsubMessage = new PubsubMessage();
	            pubsubMessage.encodeData(pubsub.getMessage().getBytes("UTF-8"));
	            PublishRequest publishRequest = new PublishRequest();
	            publishRequest.setMessages(ImmutableList.of(pubsubMessage));

	            this.pubsub.projects().topics().publish(fullTopicName, publishRequest).execute();
        	} catch(IOException e){}
        }
	}
	
}