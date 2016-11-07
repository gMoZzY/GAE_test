package se.test.resouce;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


import se.test.dao.Datastore;
import se.test.entity.pojo.TestEntity;
import se.test.util.Util;

public class SetResource extends ServerResource {

    private Datastore datastore;
    
    public SetResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.datastore = (Datastore) getContext().getAttributes().get(Util.DATASTORE_DAO_ID);
    }
    
    @Get("json")
    public Representation handleGet() {
    	
    	Map<String, Object> data = new HashMap<String, Object>();
    	
    	data.put("col3", "Hello!");
    	data.put("col2", 123);
    	data.put("col1", Calendar.getInstance().getTimeInMillis());
    	
    	datastore.setEntity("Test", data);
    	
    	JSONArray json = new JSONArray();
    	json.put("Success");
    	
        return new JsonRepresentation(json);       
    }
    
    @Post("json")
    public Representation handlePost(TestEntity entity)
    {
    	//Should probably have some kind of check for kind and properties.
		datastore.setEntity(entity.getKind(), entity.getProperties());

		JSONArray jsonArray = new JSONArray();
		jsonArray.put("Success");    	
        return new JsonRepresentation(jsonArray);    
    }
    
}
