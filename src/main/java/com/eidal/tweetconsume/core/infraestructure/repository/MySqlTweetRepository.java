package com.eidal.tweetconsume.core.infraestructure.repository;

import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.repository.TweetRepository;
import com.eidal.tweetconsume.core.infraestructure.mapper.TweetMapper;
import com.eidal.tweetconsume.core.infraestructure.repository.entity.TweetDbEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MySqlTweetRepository implements TweetRepository {

  private final SpringDataMySqlTweetRepository springDataMySqlTweetRepository;

  private final TweetMapper tweetMapper;

  @Override
  public void save(Tweet tweet) {
    springDataMySqlTweetRepository.saveAndFlush(tweetMapper.mapToTweetEntity(tweet));
  }

  @Override
  public Optional<Tweet> getById(UUID id) {
    Optional<TweetDbEntity> tweetEntity = springDataMySqlTweetRepository.findById(id);
    return tweetEntity.map(tweetMapper::mapFromTweetEntity);
  }

  @Override
  public List<Tweet> getValidatedTweetsByUser(String user) {
    return tweetMapper.mapFromTweetEntityToList(springDataMySqlTweetRepository.findByUserAndValidatedIsTrue(user));
  }

  @Override
  public List<Tweet> getAllTweets() {
    return tweetMapper.mapFromTweetEntityToList(springDataMySqlTweetRepository.findAll());
  }
}
