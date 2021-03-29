package com.eidal.tweetconsume.core.infraestructure.controller;

import com.eidal.tweetconsume.core.application.TweetFinder;
import com.eidal.tweetconsume.core.application.TweetValidator;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.TweetResponse;
import com.eidal.tweetconsume.core.infraestructure.mapper.TweetMapper;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TweetController {

  private final TweetFinder tweetFinder;

  private final TweetValidator tweetValidator;

  private final TweetMapper tweetMapper;

  public List<TweetResponse> getTweets() {
    return tweetMapper.mapToTweetRestResponseList(tweetFinder.getAllTweets());
  }

  public List<TweetResponse> getValidatedTweetsByUser(String user) {
    return tweetMapper.mapToTweetRestResponseList(tweetFinder.getValidatedTweetsByUser(user));
  }

  public void validateTweet(UUID tweetId) {
    tweetValidator.validate(tweetId);
  }
}
