package se.test.dao;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public interface Datastore {

	public List<Entity> getEntityList(String kind, Integer limit);
	public void deleteEntity(String kind, long id);
	public void updateEntity(String kind, Map<String, Object> properties, long id);
	public void setEntity(String kind, Map<String, Object> properties);	

}
