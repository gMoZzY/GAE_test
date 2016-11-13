package se.test.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.ListTopicsResponse;
import com.google.api.services.pubsub.model.Topic;

import se.test.pojo.PubsubPojo;



public class TopicPubsubImpl implements se.test.dao.TopicPubsub {

	Pubsub pubsub;
	
	private final String PROJECT_ID = "GAE_test"; 
	
	public TopicPubsubImpl()
	{
		
		this.pubsub = new Pubsub.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), null)
				.setRootUrl("http://localhost:6666").build();
				
		//pubsub = PubSubOptions.newBuilder().setHost("localhost:6666").build().getService();

	}
	
	@Override
	public PubsubPojo createTopic(PubsubPojo topic) 
	{
		
		String fullName = topic.getName();
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
		 * cloud way
		TopicInfo topicInfo = TopicInfo.of(name);
		return this.pubsub.create(topicInfo);
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
	
}