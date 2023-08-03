package com.programing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicSendMail(){
        return TopicBuilder.name("send-mail")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicCancelsOrder(){
        return TopicBuilder.name("cancel-order")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicCreateOrder(){
        return TopicBuilder.name("create-order")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
