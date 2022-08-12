package com.sluno.itf.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicsConfiguration {

    @Bean
    public NewTopic change_events(){
        return TopicBuilder.name("change_events").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic checked_events(){
        return TopicBuilder.name("checked_events").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic results_of_processing(){
        return TopicBuilder.name("results_of_processing").partitions(1).replicas(1).build();
    }
}
