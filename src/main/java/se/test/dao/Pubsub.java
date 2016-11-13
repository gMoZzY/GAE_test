package se.test.dao;

import java.util.List;

import se.test.pojo.PubsubPojo;


public interface Pubsub {

	/*Topic*/
	public PubsubPojo setTopic(PubsubPojo pubsub);
	public List<PubsubPojo> getTopics(String projectId);
	public void deleteTopic(PubsubPojo pubsub);
	
	/*Subscription*/
	public PubsubPojo setSubscription(PubsubPojo pubsub);
	public List<PubsubPojo> getSubscription(String projectId);
	public void deleteSubscription(PubsubPojo pubsub);
	
	/*Message*/
	public void sendMessage(PubsubPojo pubsub);
	
	
}
