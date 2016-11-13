package se.test.pojo;
	   

import com.google.api.services.pubsub.model.Subscription;

public class SubscriptionPubsubPojo extends PubsubPojo {
	
	private String pushEndpoint;
	private String topicName;
		
	public SubscriptionPubsubPojo() 
	{
		super.setType("subscriptions");
	}
	
	public SubscriptionPubsubPojo(Subscription pubsub) 
	{
		super.setType("subscriptions");
		setName(((Subscription)pubsub).getName());
		setTopicName(((Subscription)pubsub).getTopic());
		setPushEndpoint(((Subscription)pubsub).getPushConfig().getPushEndpoint());
	}

	public String getPushEndpoint() {
		return pushEndpoint;
	}

	public void setPushEndpoint(String pushEndpoint) {
		this.pushEndpoint = pushEndpoint;
	}

	public String getTopicName() 
	{
		return topicName;
	}

	public void setTopicName(String topicName) 
	{
		this.topicName = topicName;
	}
	
	
	
}
