package com.eidal.tweetconsume.core.application;

import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TweetPersister {

  private final TweetRepository tweetRepository;


  public void persist(Tweet tweet) {

    tweetRepository.save(tweet);
  }
}
