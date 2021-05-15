package com.agreggio.challenge.birras.santander.meet.up.service;

public interface MessageMqService {

    /**
     * Receive an object convert to Json and put on queue
     *
     * @param message {@link Object}
     */
    void send(Object message);
}
