package se.test.resouce;

import java.io.IOException;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.api.services.pubsub.model.PubsubMessage;

import se.test.pojo.MessagePojo;

public class MessagePubsubResource extends ServerResource {
    
	public MessagePubsubResource() {}
 	
    @Post("json")
    public void handlePost(MessagePojo message)
    {
        try
        {
        	PubsubMessage pubsubMessage = new PubsubMessage();
        	pubsubMessage.putAll(message.getMessage());
        	System.out.println(new String(pubsubMessage.decodeData(), "UTF-8"));        	
        } catch (IOException e)
        {}
    	getResponse().setStatus(Status.SUCCESS_OK);
    }
}
