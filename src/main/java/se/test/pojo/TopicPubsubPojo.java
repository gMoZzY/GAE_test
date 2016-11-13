package se.test.pojo;
	   
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.api.services.pubsub.model.Topic;

public class TopicPubsubPojo extends PubsubPojo {
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String message;
	
	public TopicPubsubPojo() 
	{
		super.setType("topics");
	}
	
	public TopicPubsubPojo(Topic pubsub) 
	{
		super.setType("topics");
		setName(((Topic)pubsub).getName());
	}

	
	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}
}
