package se.test.pojo;

public class CachePojo {
	
	private Object key;
	private Object value;
	private Object expire;
	
	public CachePojo() 
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
