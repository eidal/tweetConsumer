package com.eidal.tweetconsume.core.application;

import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.repository.TweetRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TweetFinder {

  private final TweetRepository tweetRepository;

  public List<Tweet> getAllTweets() {
    return tweetRepository.getAllTweets();
  }

  public List<Tweet> getValidatedTweetsByUser(String user) {
    return tweetRepository.getValidatedTweetsByUser(user);
  }

}
