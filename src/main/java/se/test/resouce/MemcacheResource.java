package se.test.resouce;


import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.appengine.api.memcache.Stats;

import se.test.dao.Memcache;
import se.test.pojo.CachePojo;
import se.test.util.Util;

public class MemcacheResource extends ServerResource {

	private Memcache memcache;
    
    public MemcacheResource() {}
    
    protected void doInit() throws ResourceException 
    {
    	this.memcache = (Memcache) getContext().getAttributes().get(Util.MEMCACHE_DAO_ID);
    }

    @Get("json")
    public Representation handleGet() 
    {
    	String key = getRequest().getResourceRef().getQueryAsForm().getFirstValue("key");
    	if(key == null)
    	{
    		return new JacksonRepresentation<Stats>(this.memcache.getStats());
    	}

    	Object cache = this.memcache.getMemcache(key);	
    	return new JacksonRepresentation<Object>(cache);
    }
    
    @Post("json")
    public Representation handlePost(CachePojo cache)
    {
    	this.memcache.setMemcache(cache.getKey(), cache.getValue(), cache.getExpire());
    	return new JsonRepresentation("Success!");
    }
    
    @Delete("json")
    public Representation handleDelete(CachePojo cache)
    {
    	this.memcache.deleteMemcache(cache.getKey());
    	return new JsonRepresentation("Success!");
    }
    

}
