package com.agreggio.challenge.birras.santander.meet.up.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class JmsConfig {

    @Value("${queue.mp}")
    public String queue;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queue);
    }

}
