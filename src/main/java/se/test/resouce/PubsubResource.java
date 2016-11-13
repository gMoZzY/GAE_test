package se.test.resouce;

import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.api.services.pubsub.model.Topic;

import se.test.dao.Pubsub;
import se.test.pojo.TopicPojo;
import se.test.util.Util;

public class PubsubResource extends ServerResource {
	
	private Pubsub pubsub;
    
    public PubsubResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.pubsub = (Pubsub) getContext().getAttributes().get(Util.PUBSUB_DAO_ID);
    }
        
   
    @Get("json")
    public Representation handleGet() 
    { 
    	String projectId = getRequest().getResourceRef().getQueryAsForm().getFirstValue("projectId");
    	return new JacksonRepresentation<List<TopicPojo>>(this.pubsub.getTopics(projectId));
    }

    @Post("json")
    public Representation handlePost(TopicPojo topic)
    {
    	return new JacksonRepresentation<TopicPojo>(this.pubsub.createTopic(topic));
    }
    
    @Put("json")
    public Representation handlePut()
    {

        return new JsonRepresentation("Success");    
    }
    
    @Delete("json")
    public Representation handleDelete()
    {

        return new JsonRepresentation("Success");    
    }
}
