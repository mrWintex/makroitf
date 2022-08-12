package com.sluno.itf.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.sql.SQLOutput;

@Configuration
public class ErrorHandlerConfig {
    @Bean
    public CommonErrorHandler commonErrorHandler(){
        return new CommonErrorHandler() {
            @Override
            public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
                String topic = record.topic();
                Integer partition = record.partition();
                Long offset = record.offset();

                TopicPartition topicPartition = new TopicPartition(topic, partition);
                consumer.seek(topicPartition, offset + 1);

                return CommonErrorHandler.super.handleOne(thrownException, record, consumer, container);
            }
        };
    }
}
