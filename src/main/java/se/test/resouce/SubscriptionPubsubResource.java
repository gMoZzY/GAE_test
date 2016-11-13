package se.test.resouce;

import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import se.test.dao.Pubsub;
import se.test.pojo.SubscriptionPubsubPojo;
import se.test.util.Util;

public class SubscriptionPubsubResource extends ServerResource {
    
	Pubsub pubsub;
	public SubscriptionPubsubResource() {}
	
	protected void doInit() throws ResourceException 
    {
		this.pubsub = (Pubsub) getContext().getAttributes().get(Util.PUBSUB_DAO_ID);
    }
	
	@Get("json")
    public Representation handleGet() 
    { 
    	String projectId = getRequest().getResourceRef().getQueryAsForm().getFirstValue("projectId");
    	return new JacksonRepresentation<List<SubscriptionPubsubPojo>>(this.pubsub.getSubscription(projectId));
    }
	
	
    @Post("json")
    public Representation handlePost(SubscriptionPubsubPojo pubsub)
    {
    	
    	if(pubsub.getPushEndpoint() != null && !pubsub.getPushEndpoint().isEmpty())
    	{
    		return new JacksonRepresentation<SubscriptionPubsubPojo>(this.pubsub.setSubscription(pubsub));
    	}
    	
    	
    	return new JacksonRepresentation<String>("Something went wrong");
    }
    
    @Delete
    public void handleDelete(SubscriptionPubsubPojo pubsub)
    {
    	this.pubsub.deleteSubscription(pubsub);
    }
}
