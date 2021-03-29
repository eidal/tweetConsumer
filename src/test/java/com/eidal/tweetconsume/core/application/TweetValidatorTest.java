package com.eidal.tweetconsume.core.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.exception.TweetNotFoundException;
import com.eidal.tweetconsume.core.domain.repository.TweetRepository;
import java.util.Optional;
import java.util.UUID;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TweetValidatorTest {
  TweetValidator tweetValidator;

  @Mock
  TweetRepository tweetRepository;

  private final EasyRandom factory = new EasyRandom();

  @BeforeEach
  public void setup() {
    factory.setSeed(1L);
    tweetValidator = new TweetValidator(tweetRepository);
  }

  @Test
  public void validateTweetShouldRaiseExceptionWhenTweetNotExists() {
    // Given the tweet repository not return a tweet
    UUID tweetId = UUID.randomUUID();
    Mockito.when(tweetRepository.getById(tweetId)).thenReturn(Optional.empty());

    // When we call to validate a tweet then raise a tweetnotfoundexception
    assertThrows(TweetNotFoundException.class, () -> tweetValidator.validate(tweetId));

  }

  @Test
  public void validateTweetSaveInRepositoryWhenTweetExists() {
    // Given the tweet repository return a tweet
    UUID tweetId = UUID.randomUUID();
    Tweet tweet = factory.nextObject(Tweet.class);
    Mockito.when(tweetRepository.getById(tweetId)).thenReturn(Optional.of(tweet));

    // When we call to validate the tweet
    tweetValidator.validate(tweetId);

    // Then the save repository is called
    Mockito.verify(tweetRepository, Mockito.times(1)).save(Mockito.any());
  }
}
