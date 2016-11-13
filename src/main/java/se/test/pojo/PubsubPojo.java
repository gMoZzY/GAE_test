package se.test.pojo;

public class PubsubPojo {
	
	private String name;
	private String projectId;
	private String id;
	private String type;
	
	public PubsubPojo() {}
	
	public void setName(String name)
	{
		if(name != null && !name.isEmpty() && name.contains("/"))
		{
			String[] tmp = name.split("/");
			if(tmp.length == 4)
			{
				this.setProjectId(tmp[1]);
				this.setId(tmp[3]);
			}
		}
	}
	
	private void setName() 
	{
		this.name = String.format("projects/%s/%s/%s", this.getProjectId(), this.getType(), this.getId());
	}

	public String getName()
	{
		return this.name;
	}
	
	public String getProjectId() 
	{
		return this.projectId;
	}

	public void setProjectId(String projectId) 
	{
		this.projectId = projectId;
		setName();
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
		setName();
	}
	
	private String getType()
	{
		return this.type;
	}
	
	protected void setType(String type)
	{
		this.type = type;
		setName();
	}
	
}
