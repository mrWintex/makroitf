package com.sluno.itf.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration("topicUtils")
public class TopicUtils {

    @Value("${kafka.topics.app_topic_one}")
    private String first_app_topic;
    @Value("${kafka.topics.app_topic_two}")
    private String second_app_topic;
    @Value("${kafka.topics.app_topic_three}")
    private String third_app_topic;

    public String firstTopic(){
        return first_app_topic;
    }
    public String secondTopic(){
        return second_app_topic;
    }
    public String thirdTopic(){
        return third_app_topic;
    }
}
