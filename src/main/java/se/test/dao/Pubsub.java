package se.test.dao;

import java.util.List;

import se.test.pojo.TopicPojo;


public interface Pubsub {

	public TopicPojo createTopic(TopicPojo topic);
	public List<TopicPojo> getTopics(String projectId);
	
	
}
