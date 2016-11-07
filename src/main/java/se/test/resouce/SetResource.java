package se.test.resouce;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


import se.test.dao.Datastore;
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
    public Representation handlePost(JSONObject jsonRequest)
    {
    	JSONArray jsonArray = new JSONArray();
    	try {
			if(jsonRequest.isNull("kind") || jsonRequest.isNull("properties"))
			{
				jsonArray.put("kind and properties keys required");
		        return new JsonRepresentation(jsonArray);
			}
			
			String kind = (String)jsonRequest.get("kind");
			Map<String, Object> properties = new HashMap<String, Object>();
			JSONObject jsonProperties = jsonRequest.getJSONObject("properties");
			for(String propertyKey : JSONObject.getNames(jsonProperties))
			{
				properties.put(propertyKey, jsonProperties.get(propertyKey));
			}
	
			datastore.setEntity(kind, properties);
		} catch (JSONException e) {
			e.printStackTrace();			
	    	jsonArray.put("Fail");
	        return new JsonRepresentation(jsonArray);
		}

    	jsonArray.put("Success");    	
        return new JsonRepresentation(jsonArray);    
    }
    
}
