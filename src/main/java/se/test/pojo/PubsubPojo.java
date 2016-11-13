package se.test.pojo;
	   
import com.google.api.client.json.GenericJson;
import com.google.api.services.pubsub.model.Subscription;
import com.google.api.services.pubsub.model.Topic;

public class PubsubPojo {

	private String topicName;
	private String subscriptionName;
	private String projectId;
	private String topicId;
	
	public PubsubPojo() {}
	
	public PubsubPojo(GenericJson pubsub) 
	{
		if(pubsub instanceof Topic)
		{
			setName(((Topic)pubsub).getName());
		}
		else if(pubsub instanceof Subscription)
		{
			setName(((Subscription)pubsub).getName());
		}
	}

	public void setTopicName(String topicName) 
	{
		this.topicName = topicName;
	}
	
	public String getTopicName() 
	{
		return topicName;
	}
	
	public void setSubscriptionName(String subscriptionName) 
	{
		this.subscriptionName = subscriptionName;
	}
	
	public String getSubscriptionName() 
	{
		return subscriptionName;
	}
	
	public void setName(String name)
	{
		if(name != null && !name.isEmpty() && name.contains("/"))
		{
			String[] tmp = name.split("/");
			if(tmp.length == 4)
			{
				this.setProjectId(tmp[1]);
				this.setTopicId(tmp[3]);
			}
		}
	}
	
	private void setName() 
	{
		this.topicName = String.format("projects/%s/topics/%s", this.getProjectId(), this.getTopicId());
		this.subscriptionName = String.format("projects/%s/subscriptions/%s", this.getProjectId(), this.getTopicId());
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
		setName();
		
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
		setName();
	}
}
