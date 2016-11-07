package se.test.resouce;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import se.test.dao.Datastore;
import se.test.entity.pojo.TestEntity;
import se.test.util.Util;

import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
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
/*
    	For parameterizied URI use:
    	router.attach("/get/{param}", Resource.class);
   		getRequestAttributes().get("param")
*/
    	
    	String kind = getRequest().getResourceRef().getQueryAsForm().getFirstValue("kind");
    	String pLimit = getRequest().getResourceRef().getQueryAsForm().getFirstValue("limit");
    	Integer limit = 1000;
    	try{
    		limit = Integer.parseInt(pLimit);
    	}catch (Exception e) {}
    	
    	List<Entity> list = datastore.getEntityList(kind, limit);
    	List<TestEntity> result = new ArrayList<TestEntity>();
    	for(Entity tmp : list)
		{
    		result.add(new TestEntity(tmp));
		}
        return new JacksonRepresentation<List<TestEntity>>(result);
    }

}
