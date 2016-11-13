package se.test.pojo;

import java.util.Map;

public class MessagePojo {

	Map<String, Object> message;
	String subscription;
	
	public MessagePojo(){}

	public Map<String, Object> getMessage() 
	{
		return message;
	}

	public void setMessage(Map<String, Object> message) 
	{
		this.message = message;
	}

	public String getSubscription() 
	{
		return subscription;
	}

	public void setSubscription(String subscription) 
	{
		this.subscription = subscription;
	}
	
	
	
}
