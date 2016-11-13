package se.test.resouce;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


public class DefaultResource extends ServerResource {
    
	public DefaultResource() {}
	
	protected void doInit() throws ResourceException 
    {
		//(INTERFACE) getContext().getAttributes().get("MAP_KEY");
    }
	
    @Get
    public Representation handleGet() 
    {
    	String getParam = getRequest().getResourceRef().getQueryAsForm().getFirstValue("get");
    	
        return new JacksonRepresentation<String>("Hello GET world!");
    }
 	
    @Post("json")
    public Representation handlePost()
    {
    	return new JacksonRepresentation<String>("Hello POST world!");
    }
    
    @Put("json")
    public Representation handlePut()
    {
    	return new JacksonRepresentation<String>("Hello PUT world!");
    }

    @Delete("json")
    public Representation handleDelete()
    {    	
    	return new JacksonRepresentation<String>("Hello DELETE world!");
    }
}
