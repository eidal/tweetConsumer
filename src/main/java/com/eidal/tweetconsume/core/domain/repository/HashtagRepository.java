package com.eidal.tweetconsume.core.domain.repository;

import com.eidal.tweetconsume.core.domain.Hashtag;
import java.util.List;

public interface HashtagRepository {
    void incrementRepetitions(Hashtag hashtag);

    List<Hashtag> findAllOrderByRepetitions();
}
