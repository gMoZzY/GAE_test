package se.test.dao.impl;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import se.test.dao.Memcache;

public class MemcacheImpl implements Memcache {

	private final MemcacheService memcacheService;
//	private final AsyncMemcacheService asyncMemcacheService; 
	
	public MemcacheImpl()
	{
		this.memcacheService = MemcacheServiceFactory.getMemcacheService();
//		this.asyncMemcacheService = MemcacheServiceFactory.getAsyncMemcacheService();
	}
	
	
	
	
}
