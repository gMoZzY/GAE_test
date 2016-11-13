package se.test.pojo;

import com.google.api.services.pubsub.model.Topic;

public class PubsubPojo {

	private String name;
	private String projectId;
	private String topicId;
	
	public PubsubPojo() {}
	
	public PubsubPojo(Topic topic) 
	{
		setName(topic.getName());
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name)
	{
		if(name != null && !name.isEmpty() && name.contains("/"))
		{
			String[] tmp = name.split("/");
			if(tmp.length == 4)
			{
				this.setProjectId(tmp[1]);
				this.setTopicId(tmp[3]);
			}
		}
	}
	
	private void setName() {
		this.name = String.format("projects/%s/topics/%s", this.getProjectId(), this.getTopicId());
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
		setName();
		
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
		setName();
	}
}
