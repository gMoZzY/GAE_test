package se.test.dao;

import java.util.List;

import se.test.pojo.SubscriptionPubsubPojo;
import se.test.pojo.TopicPubsubPojo;


public interface Pubsub {

	/*Topic*/
	public TopicPubsubPojo setTopic(TopicPubsubPojo pubsub);
	public List<TopicPubsubPojo> getTopics(String projectId);
	public void deleteTopic(TopicPubsubPojo pubsub);
	
	/*Subscription*/
	public SubscriptionPubsubPojo setSubscription(SubscriptionPubsubPojo pubsub);
	public List<SubscriptionPubsubPojo> getSubscription(String projectId);
	public void deleteSubscription(SubscriptionPubsubPojo pubsub);
	
	/*Message*/
	public void sendMessage(TopicPubsubPojo pubsub);
	
	
}
