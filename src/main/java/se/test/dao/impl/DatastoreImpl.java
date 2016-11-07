package se.test.dao.impl;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import se.test.dao.Datastore;

public class DatastoreImpl implements Datastore {

	private final DatastoreService datastore;
    
    
    public DatastoreImpl()
    {
    	this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

	public List<Entity> getEntityList(String kind, Integer limit) {
		Query query = new Query(kind);
		
		return datastore.prepare(query).asList(FetchOptions.Builder.withLimit(limit));
	}

	public void deleteEntity(String kind, long id)
	{
		Key key = KeyFactory.createKey(kind, id);
		datastore.delete(key);
	}

	public void updateEntity(String kind, Map<String, Object> properties, long id)
	{
		Key key = KeyFactory.createKey(kind, id);
		Entity entity = new Entity(key);
		for(Map.Entry<String, Object> property : properties.entrySet())
		{
			entity.setProperty(property.getKey(), property.getValue());
		}
		datastore.put(entity);
	}
	
	public Entity setEntity(String kind, Map<String, Object> properties) {
		
		Entity entity = new Entity(kind);
		for(Map.Entry<String, Object> property : properties.entrySet())
		{
			entity.setProperty(property.getKey(), property.getValue());
		}
		datastore.put(entity);
		
		return entity;
	}

   
	
}
