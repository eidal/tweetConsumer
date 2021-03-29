package com.eidal.tweetconsume.core.domain;

import twitter4j.TwitterException;

public interface TweetConsumer {

  void consumeTweet(String tweetJson) throws TwitterException;
}
