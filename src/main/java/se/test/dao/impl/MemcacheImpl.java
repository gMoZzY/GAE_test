package se.test.dao.impl;

import java.util.Date;
import java.util.logging.Level;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.Stats;
import com.google.appengine.api.search.query.ExpressionParser.negation_return;

import se.test.dao.Memcache;

public class MemcacheImpl implements Memcache {

	private final MemcacheService memcacheService;
//	private final AsyncMemcacheService asyncMemcacheService; 
	
	public MemcacheImpl()
	{
		this.memcacheService = MemcacheServiceFactory.getMemcacheService();
//		this.asyncMemcacheService = MemcacheServiceFactory.getAsyncMemcacheService();
		this.memcacheService.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
	}
	
	@Override
	public Object getMemcache(Object key) {
		return this.memcacheService.get(key);
	}
	
	@Override
	public void deleteMemcache(Object key) {
		this.memcacheService.delete(key);
	}
	
	@Override
	public Stats getStats() {
		return this.memcacheService.getStatistics();
	}
	
	@Override
	public void setMemcache(Object key, Object value, Object expire) 
	{

		Expiration exp = null;
		if(expire != null)
		{
			if(expire instanceof Date)
			{
				exp = Expiration.onDate((Date)expire); 
			}
			else if(expire instanceof Integer)
			{
				exp = Expiration.byDeltaSeconds((Integer)expire);
			}
		}

			this.memcacheService.put(key, value, exp);	
	}
	
	
}
