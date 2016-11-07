package se.test.resouce;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DefaultResource extends ServerResource {
    
    @Get
    public String represent() {
        return "Hello world!";
    }
 	

}
