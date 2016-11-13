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

import se.test.dao.TopicPubsub;
import se.test.pojo.PubsubPojo;
import se.test.util.Util;

public class TopicPubsubResource extends ServerResource {
	
	private TopicPubsub pubsub;
    
    public TopicPubsubResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.pubsub = (TopicPubsub) getContext().getAttributes().get(Util.PUBSUB_DAO_ID);
    }
        
   
    @Get("json")
    public Representation handleGet() 
    { 
    	String projectId = getRequest().getResourceRef().getQueryAsForm().getFirstValue("projectId");
    	return new JacksonRepresentation<List<PubsubPojo>>(this.pubsub.getTopics(projectId));
    }

    @Post("json")
    public Representation handlePost(PubsubPojo topic)
    {
    	return new JacksonRepresentation<PubsubPojo>(this.pubsub.createTopic(topic));
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
