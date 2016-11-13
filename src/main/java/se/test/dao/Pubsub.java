package se.test.dao;

import java.util.List;

import se.test.pojo.PubsubPojo;


public interface Pubsub {

	public PubsubPojo setTopic(PubsubPojo topic);
	public List<PubsubPojo> getTopics(String projectId);
	
	public PubsubPojo setSubscription(PubsubPojo subscription);
	public PubsubPojo getSubscription(String subscription);
	
	
}
