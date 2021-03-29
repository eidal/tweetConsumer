package com.eidal.tweetconsume.core.application;

import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.exception.TweetNotFoundException;
import com.eidal.tweetconsume.core.domain.repository.TweetRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TweetValidator {

  private final TweetRepository tweetRepository;

  public void validate(UUID tweetId) {

    Optional<Tweet> optionalTweet = tweetRepository.getById(tweetId);

    if (optionalTweet.isPresent()) {
      Tweet tweet = optionalTweet.get();
      tweet.setValidated(true);
      tweetRepository.save(tweet);
    } else {
      log.error("Error! The tweet id {} not exists", tweetId);
      throw new TweetNotFoundException("Error! The tweet id not exists");
    }
  }
}
