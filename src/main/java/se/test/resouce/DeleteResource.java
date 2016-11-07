package se.test.resouce;

import org.json.JSONArray;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


import se.test.dao.Datastore;
import se.test.entity.pojo.TestEntity;
import se.test.util.Util;

public class DeleteResource extends ServerResource {

    private Datastore datastore;
    
    public DeleteResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.datastore = (Datastore) getContext().getAttributes().get(Util.DATASTORE_DAO_ID);
    }
    
    @Delete("json")
    public Representation handlePost(TestEntity entity)
    {
    	//Should probably have some kind of check for kind and properties.
		datastore.deleteEntity(entity.getKind(), entity.getId());

		JSONArray jsonArray = new JSONArray();
		jsonArray.put("Success");    	
        return new JsonRepresentation(jsonArray);    
    }
    
}
