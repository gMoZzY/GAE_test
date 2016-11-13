package se.test.resouce;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import se.test.dao.Datastore;
import se.test.pojo.TestPojo;
import se.test.util.Util;

import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
import java.util.List;

public class DatastoreResource extends ServerResource {

	private Datastore datastore;
    
    public DatastoreResource() {}
    
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
    	List<TestPojo> result = new ArrayList<TestPojo>();
    	for(Entity tmp : list)
		{
    		result.add(new TestPojo(tmp));
		}
        return new JacksonRepresentation<List<TestPojo>>(result);
    }

    @Post("json")
    public Representation handlePost(TestPojo entity)
    {
    	//Should probably have some kind of check for kind and properties.
		datastore.setEntity(entity.getKind(), entity.getProperties());

        return new JsonRepresentation("Success");    
    }
    
    @Put("json")
    public Representation handlePut(TestPojo entity)
    {
    	//Should probably have some kind of check for kind and properties.
		datastore.updateEntity(entity.getKind(), entity.getProperties(), entity.getId());

        return new JsonRepresentation("Success");    
    }
    
    @Delete("json")
    public Representation handleDelete(TestPojo entity)
    {
    	//Should probably have some kind of check for kind and properties.
		datastore.deleteEntity(entity.getKind(), entity.getId());

        return new JsonRepresentation("Success");    
    }
}
