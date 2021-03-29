package com.eidal.tweetconsume.core.application;

import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.repository.HashtagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HashtagPersister {

  private final HashtagRepository hashtagRepository;

  public void persist(Hashtag hashtag) {
    hashtagRepository.incrementRepetitions(hashtag);
  }

}
