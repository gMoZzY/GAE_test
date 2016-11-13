package se.test.dao;

import java.util.List;

import se.test.entity.pojo.TopicEntity;


public interface Pubsub {

	public TopicEntity createTopic(TopicEntity topic);
	public List<TopicEntity> getTopics(String projectId);
	
	
}
