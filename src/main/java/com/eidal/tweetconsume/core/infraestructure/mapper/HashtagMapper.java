package com.eidal.tweetconsume.core.infraestructure.mapper;

import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.Location;
import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.HashtagResponse;
import com.eidal.tweetconsume.core.infraestructure.repository.entity.HashtagDbEntity;
import com.eidal.tweetconsume.core.infraestructure.repository.entity.TweetDbEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;

@Component
public class HashtagMapper {

  public List<Hashtag> mapFromHashtagTwitter(HashtagEntity[] hashtagList) {
    return Arrays.stream(hashtagList).map(HashtagEntity::getText).map(Hashtag::new).collect(Collectors.toList());
  }

  public Hashtag mapFromHashtagEntity(@NotNull HashtagDbEntity hashtagDbEntity) {
    return Optional.ofNullable(hashtagDbEntity).map(HashtagDbEntity::getName).map(Hashtag::new).orElse(null);
  }

  public List<Hashtag> mapFromHashtagEntityList(Collection<HashtagDbEntity> hashtagDbEntities) {
    return hashtagDbEntities.stream().map(this::mapFromHashtagEntity).collect(Collectors.toList());
  }

  public HashtagResponse mapToHashtagResponse(@NotNull Hashtag hashtag) {
    return Optional.ofNullable(hashtag).map(Hashtag::getName).map(HashtagResponse::new).orElse(null);
  }

  public List<HashtagResponse> mapToHashtagResponseList(List<Hashtag> hashtags) {
    return hashtags.stream().map(this::mapToHashtagResponse).collect(Collectors.toList());
  }
}
