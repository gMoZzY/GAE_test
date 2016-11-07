package se.test.resouce;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class DefaultResource extends ServerResource {
    
    @Get
    public String handleGet() 
    {
        return "Hello GET world!";
    }
 	
    @Post
    public String handlePost()
    {
    	return "Hello POST world!";
    }
    
    @Put
    public String handlePut()
    {
    	return "Hello PUT world!";
    }

    @Delete
    public String handleDelete()
    {
    	return "Hello DELETE world!";
    }
}
