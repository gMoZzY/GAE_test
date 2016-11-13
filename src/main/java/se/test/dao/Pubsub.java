package se.test.dao;

import java.util.List;

import se.test.pojo.PubsubPojo;


public interface Pubsub {

	public PubsubPojo setTopic(PubsubPojo pubsubPojo);
	public List<PubsubPojo> getTopics(String projectId);
	
	public PubsubPojo setSubscription(PubsubPojo pubsubPojo);
	public PubsubPojo getSubscription(String projectId);
	
	
}
