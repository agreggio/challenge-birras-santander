package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;
import java.util.Queue;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
public class MessageMqServiceImplTest {

    @Spy
    @InjectMocks
    MessageMqServiceImpl messageMqService;


    private static final String message = "this is message";

    @Before
    public void init() {
        ReflectionTestUtils.setField(messageMqService, "failedMessages", new ArrayList<>());

    }

    @Test
    public void testSend_success() {

        doNothing().when(messageMqService).send(message);
        messageMqService.send(message);
        verify(messageMqService).send(message);
    }

    @Test
    public void testSend_nulleable_message() {

        doNothing().when(messageMqService).send(null);
        messageMqService.send(null);
        verify(messageMqService).send(null);

    }

    @Test
    public void testSend_error() {

        doNothing().when(messageMqService).send(message);
        messageMqService.send(message);
        verify(messageMqService).send(message);

    }

    @Test
    public void testReSend() {
        doNothing().when(messageMqService).reSend();
        messageMqService.reSend();
        verify(messageMqService).reSend();

    }

}
