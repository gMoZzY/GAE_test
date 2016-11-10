package se.test.entity.pojo;

public class CacheEntity {
	
	private Object key;
	private Object value;
	private Object expire;
	
	public CacheEntity() 
	{
	
	}
	
	public Object getKey() 
	{
		return this.key;
	}
	
	public void setKey(Object key) 
	{
		this.key = key;
	}
	
	public Object getValue() 
	{
		return this.value;
	}
	
	public void setValue(Object value) 
	{
		this.value = value;
	}
	
	public Object getExpire() 
	{
		return this.expire;
	}
	
	public void setExpire(Object expire) 
	{
		this.expire = expire;
	}	
}
