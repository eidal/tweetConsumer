package com.eidal.tweetconsume.adapter.service;

import com.eidal.tweetconsume.core.infraestructure.controller.HashtagController;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.HashtagResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HashtagService {

  private final HashtagController hashtagController;

  public List<HashtagResponse> getHashtagsMoreUsed(Long numHashtags) {
    return hashtagController.getHashtagsMoreUsed(numHashtags);
  }

}
