package se.test.dao;

import com.google.appengine.api.memcache.Stats;

public interface Memcache {

	public Object getMemcache(Object key);
	public Stats getStats();
	public void setMemcache(Object key, Object value, Object expire);
	public void deleteMemcache(Object key);
	
}
