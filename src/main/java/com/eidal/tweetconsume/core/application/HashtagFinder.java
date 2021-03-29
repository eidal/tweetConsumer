package com.eidal.tweetconsume.core.application;

import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.repository.HashtagRepository;
import com.eidal.tweetconsume.core.infraestructure.config.HashtagConfig;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HashtagFinder {

  private final HashtagRepository hashtagRepository;

  private final HashtagConfig hashtagConfig;

  public List<Hashtag> getHashtagsMoreUsed(Long numHastags) {
    Long numHashtagsToFilter = Optional.ofNullable(numHastags).orElse(hashtagConfig.getHashtagsNumClasification());
    return hashtagRepository.findAllOrderByRepetitions().stream().limit(numHashtagsToFilter).collect(Collectors.toList());
  }

}
