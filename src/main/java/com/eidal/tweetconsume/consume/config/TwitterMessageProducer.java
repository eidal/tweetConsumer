package com.eidal.tweetconsume.consume.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

@Slf4j
public class TwitterMessageProducer extends MessageProducerSupport {

  private final TwitterStream twitterStream;

  private final TwitterFilterConfig twitterFilterConfig;

  private StatusListener statusListener;

  private FilterQuery filterQuery;

  public TwitterMessageProducer(TwitterStream twitterStream, MessageChannel outputChannel, TwitterFilterConfig twitterFilterConfig) {
    this.twitterStream = twitterStream;
    setOutputChannel(outputChannel);
    this.twitterFilterConfig = twitterFilterConfig;
  }

  @Override
  protected void onInit() {
    super.onInit();
    statusListener = new StatusListener();

    String[] terms = twitterFilterConfig.getTerms();

    String[] languages = twitterFilterConfig.getLanguages();

    filterQuery = new FilterQuery(0, null, terms, null, languages);
  }

  @Override
  public void doStart() {
    twitterStream.addListener(statusListener);
    twitterStream.filter(filterQuery);
  }

  @Override
  public void doStop() {
    twitterStream.cleanUp();
    twitterStream.clearListeners();
  }

  StatusListener getStatusListener() {
    return statusListener;
  }

  FilterQuery getFilterQuery() {
    return filterQuery;
  }

  class StatusListener extends StatusAdapter {

    @Override
    public void onStatus(Status status) {
      sendMessage(MessageBuilder.withPayload(status).build());
    }

    @Override
    public void onException(Exception ex) {
      log.error(ex.getMessage(), ex);
    }

    @Override
    public void onStallWarning(StallWarning warning) {
      log.warn(warning.toString());
    }

  }

}