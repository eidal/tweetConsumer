package com.eidal.tweetconsume.core.infraestructure.controller;

import com.eidal.tweetconsume.core.application.HashtagFinder;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.HashtagResponse;
import com.eidal.tweetconsume.core.infraestructure.mapper.HashtagMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HashtagController {


  private final HashtagFinder hashtagFinder;

  private final HashtagMapper hashtagMapper;

  public List<HashtagResponse> getHashtagsMoreUsed(Long numHashtags) {
    return hashtagMapper.mapToHashtagResponseList(hashtagFinder.getHashtagsMoreUsed(numHashtags));
  }

}
