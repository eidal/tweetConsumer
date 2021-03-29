package com.eidal.tweetconsume.adapter.service;

import com.eidal.tweetconsume.core.infraestructure.controller.TweetController;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.TweetResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TweetService {

  private final TweetController tweetController;


  public List<TweetResponse> getTweets() {
    return tweetController.getTweets();
  }

  public List<TweetResponse> getValidatedTweetsByUser(String user) {
    return tweetController.getValidatedTweetsByUser(user);
  }

  public void validateTweet(UUID tweetId) {
    tweetController.validateTweet(tweetId);
  }
}
