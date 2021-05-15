package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.jms.Queue;

import com.agreggio.challenge.birras.santander.common.Util.JsonUtils;
import com.agreggio.challenge.birras.santander.meet.up.service.MessageMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageMqServiceImpl implements MessageMqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageMqServiceImpl.class);

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    private List<String> failedMessages = new ArrayList<>();

    /**
     * Send message to queue mq, if it fails save the message in the list
     * {@link #failedMessages} to try later
     */
    @Override
    public void send(Object message) {

        if (message == null)
            return;

        String jsonMessage = JsonUtils.toJson(message);

        CompletableFuture.runAsync(() -> {
            try {
                LOGGER.info("Sending message: {}.", jsonMessage);

                jmsTemplate.convertAndSend(queue, jsonMessage);

                LOGGER.info("Message sent succesfully");

            } catch (Exception e) {

                LOGGER.error("Error to send message.");

                failedMessages.add(jsonMessage);
            }
        });

    }

    /**
     * Forward the messages in the list {@link #failedMessages}
     */
    @Scheduled(fixedDelayString = "${message.mq.resend.fixed.delay}")
    @Async
    public void reSend() {

        List<String> newFailedMessage = new ArrayList<>();

        failedMessages.forEach(m -> {
            try {
                jmsTemplate.convertAndSend(queue, m.toString());
            } catch (Exception e) {
                newFailedMessage.add(m);
            }
        });

        failedMessages = newFailedMessage;
    }

}
