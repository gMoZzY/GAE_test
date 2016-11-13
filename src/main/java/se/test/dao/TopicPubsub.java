package se.test.dao;

import java.util.List;

import se.test.pojo.PubsubPojo;


public interface TopicPubsub {

	public PubsubPojo createTopic(PubsubPojo topic);
	public List<PubsubPojo> getTopics(String projectId);
	
	
}
