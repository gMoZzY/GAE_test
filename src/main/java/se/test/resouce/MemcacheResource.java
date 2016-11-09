package se.test.resouce;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import se.test.dao.Memcache;
import se.test.util.Util;

public class MemcacheResource extends ServerResource {

	private Memcache memcache;
    
    public MemcacheResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.memcache = (Memcache) getContext().getAttributes().get(Util.MEMCACHE_DAO_ID);
    }

    
    
    

}
