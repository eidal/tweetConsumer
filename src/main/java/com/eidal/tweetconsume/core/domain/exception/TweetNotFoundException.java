package com.eidal.tweetconsume.core.domain.exception;

public class TweetNotFoundException extends RuntimeException {

    public TweetNotFoundException(String errorMessage) {
      super(errorMessage);
    }

	public TweetNotFoundException(String errorMessage, Throwable error) {
      super(errorMessage, error);
    }
}
