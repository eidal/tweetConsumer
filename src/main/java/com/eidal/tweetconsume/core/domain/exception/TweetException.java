package com.eidal.tweetconsume.core.domain.exception;

public class TweetException extends RuntimeException {

    public TweetException(String errorMessage) {
      super(errorMessage);
    }

	public TweetException(String errorMessage, Throwable error) {
      super(errorMessage, error);
    }
}
