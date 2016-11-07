package se.test.dao.impl;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
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
