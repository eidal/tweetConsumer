package com.eidal.tweetconsume.core.infraestructure.jms;

import com.eidal.tweetconsume.core.application.HashtagPersister;
import com.eidal.tweetconsume.core.application.TweetPersister;
import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.TweetConsumer;
import com.eidal.tweetconsume.core.domain.exception.TweetException;
import com.eidal.tweetconsume.core.infraestructure.mapper.HashtagMapper;
import com.eidal.tweetconsume.core.infraestructure.mapper.TweetMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

@Component
@Slf4j
@AllArgsConstructor
public class JmsTweetConsumer implements TweetConsumer {

  private final TweetPersister tweetPersister;

  private final HashtagPersister hashtagPersister;

  private final TweetMapper tweetMapper;

  private final HashtagMapper hashtagMapper;

  @JmsListener(destination = "${active-mq.queue.tweet}")
  public void consumeTweet(String tweetJson) throws TweetException {
    try{
      Status statusTwitter = TwitterObjectFactory.createStatus(tweetJson);
      log.info("Consuming for persist tweet ID: {}", statusTwitter.getId());
      Tweet tweet = tweetMapper.mapFromStatusTwitter(statusTwitter);
      this.tweetPersister.persist(tweet);
      List<Hashtag> hashtags = hashtagMapper.mapFromHashtagTwitter(statusTwitter.getHashtagEntities());
      hashtags.forEach(hashtagPersister::persist);
    } catch(Exception e) {
      log.error("Exception processing tweet: {}", e.toString());
      throw new TweetException("Error processing and persisting tweet", e);
    }

  }
}
