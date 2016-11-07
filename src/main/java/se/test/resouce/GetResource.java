package se.test.resouce;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import se.test.dao.Datastore;
import se.test.util.Util;

import com.google.appengine.api.datastore.Entity;
import java.util.List;

public class GetResource extends ServerResource {

	private Datastore datastore;
    
    public GetResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.datastore = (Datastore) getContext().getAttributes().get(Util.DATASTORE_DAO_ID);
    }
        
    @Get("json")
    public Representation handleGet() 
    { 
    	String kind = getRequest().getResourceRef().getQueryAsForm().getFirstValue("kind");
    	String pLimit = getRequest().getResourceRef().getQueryAsForm().getFirstValue("limit");
    	Integer limit = 1000;
    	try{
    		limit = Integer.parseInt(pLimit);
    	}catch (Exception e) {}
    	
    	List<Entity> list = datastore.getEntityList(kind, limit);
        return new JacksonRepresentation<List<Entity>>(list);
    }

}
