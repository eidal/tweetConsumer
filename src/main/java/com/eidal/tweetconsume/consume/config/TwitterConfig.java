package com.eidal.tweetconsume.consume.config;

import com.eidal.tweetconsume.consume.producer.TwitterProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Async;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;

@Slf4j
@Configuration
public class TwitterConfig {

  private final TwitterFilterConfig twitterFilterConfig;

  private final TwitterProducer twitterProducer;

  private final ObjectMapper objectMapper;

  public TwitterConfig(TwitterFilterConfig twitterFilterConfig, TwitterProducer twitterProducer,
                       ObjectMapper objectMapper) {
    this.twitterFilterConfig = twitterFilterConfig;
    this.twitterProducer = twitterProducer;
    this.objectMapper = objectMapper;
  }

  @Bean
  TwitterStreamFactory twitterStreamFactory() {
    return new TwitterStreamFactory();
  }

  @Bean
  TwitterStream twitterStream(TwitterStreamFactory twitterStreamFactory) {
    return twitterStreamFactory.getInstance();
  }

  @Bean
  MessageChannel outputChannel() {
    return MessageChannels.direct().get();
  }

  @Bean
  TwitterMessageProducer twitterMessageProducer(
          TwitterStream twitterStream, MessageChannel outputChannel) {

    return new TwitterMessageProducer(twitterStream, outputChannel, twitterFilterConfig);

  }

  @Bean
  @Async
  IntegrationFlow twitterFlow(MessageChannel outputChannel) {
    return IntegrationFlows.from(outputChannel)
            .filter((Status status) ->
                    Optional.ofNullable(status).map(Status::getUser).map(User::getFollowersCount).orElse(0) >= twitterFilterConfig.getMinFollowers())
            .handle(message -> {
              String json = null;
              try {
                json = objectMapper.writeValueAsString(message.getPayload());
              } catch (JsonProcessingException e) {
                e.printStackTrace();
              }
              if (Objects.nonNull(json)) {
                twitterProducer.sendTweet(json);
              }
            })
            .get();
  }

}