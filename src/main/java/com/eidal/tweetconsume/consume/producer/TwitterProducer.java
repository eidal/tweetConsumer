package com.eidal.tweetconsume.consume.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
@Slf4j
public class TwitterProducer {

  @Autowired
  JmsTemplate jmsTemplate;

  @Value("${active-mq.queue.tweet}")
  private String queue;

  public void sendTweet(String message){
    try{
      log.info("Attempting Send message to tweet queue: {} ", queue);
      jmsTemplate.convertAndSend(queue, message);
    } catch(Exception e){
      log.error("Received Exception during send Message: ", e);
    }
  }
}