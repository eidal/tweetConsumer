package com.eidal.tweetconsume.core.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.repository.HashtagRepository;
import com.eidal.tweetconsume.core.infraestructure.config.HashtagConfig;
import java.util.List;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HashtagFinderTest {

  HashtagFinder hashtagFinder;

  @Mock
  HashtagRepository hashtagRepository;

  @Mock
  HashtagConfig hashtagConfig;

  private final EasyRandom factory = new EasyRandom();

  private final Long HASHTAG_CONFIG_LIMITATION = 10L;

  @BeforeEach
  public void setup() {
    factory.setSeed(1L);
    List<Hashtag> hashtags = factory.objects(Hashtag.class, 15).collect(Collectors.toList());
    hashtagFinder = new HashtagFinder(hashtagRepository, hashtagConfig);
    Mockito.when(hashtagConfig.getHashtagsNumClasification()).thenReturn(HASHTAG_CONFIG_LIMITATION);
    Mockito.when(hashtagRepository.findAllOrderByRepetitions()).thenReturn(hashtags);
  }

  @Test
  public void hashtagFinderShouldReturnDefaultElementsWithoutLimitSpecified() {
    // Given the repository return a list of 15 hashtags (in setup)

    // When we call the hashtagfinder with null limitation
    List<Hashtag> hashtags = hashtagFinder.getHashtagsMoreUsed(null);

    // Then the hashtags size is the configured (10)
    assertThat(hashtags.size(), is(equalTo(HASHTAG_CONFIG_LIMITATION.intValue())));
  }

  @Test
  public void hashtagFinderShouldReturnLessElementsThanDefaultWithInferiorLimitSpecified() {
    // Given the repository return a list of 15 hashtags (in setup)

    // When we call the hashtagfinder with inferior limitation than the configured
    List<Hashtag> hashtags = hashtagFinder.getHashtagsMoreUsed(8L);

    // Then the hashtags size is the specified
    assertThat(hashtags.size(), is(equalTo(8)));
  }

  @Test
  public void hashtagFinderShouldReturnMoreElementsThanDefaultWithSuperiorLimitSpecified() {
    // Given the repository return a list of 15 hashtags (in setup)

    // When we call the hashtagfinder with inferior limitation than the configured
    List<Hashtag> hashtags = hashtagFinder.getHashtagsMoreUsed(12L);

    // Then the hashtags size is the specified
    assertThat(hashtags.size(), is(equalTo(12)));
  }
}
