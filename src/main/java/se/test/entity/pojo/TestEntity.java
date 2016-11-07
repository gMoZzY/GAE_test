package se.test.entity.pojo;

import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public class TestEntity {

	private long id; 
	private String kind;
	private Map<String, Object> properties;
	
	
	public TestEntity()
	{}

	public TestEntity(Entity entity)
	{
		setId(entity.getKey().getId());
		setKind(entity.getKind());
		setProperties(entity.getProperties());
	}

	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
		
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}	
}
