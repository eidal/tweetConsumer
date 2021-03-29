package com.eidal.tweetconsume.core.domain.repository;

import com.eidal.tweetconsume.core.domain.Tweet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TweetRepository {

  void save(Tweet tweet);

  Optional<Tweet> getById(UUID id);

  List<Tweet> getValidatedTweetsByUser(String user);

  List<Tweet> getAllTweets();
}
