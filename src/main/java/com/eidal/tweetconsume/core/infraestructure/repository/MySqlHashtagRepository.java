package com.eidal.tweetconsume.core.infraestructure.repository;

import com.eidal.tweetconsume.core.domain.Hashtag;
import com.eidal.tweetconsume.core.domain.repository.HashtagRepository;
import com.eidal.tweetconsume.core.infraestructure.mapper.HashtagMapper;
import com.eidal.tweetconsume.core.infraestructure.repository.entity.HashtagDbEntity;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MySqlHashtagRepository implements HashtagRepository {

  private final SpringDataMysqlHashtagRepository springDataMysqlHashtagRepository;

  private final HashtagMapper hashtagMapper;

  @Override
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public void incrementRepetitions(Hashtag hashtag) {
    Optional<HashtagDbEntity> optionalHashtagDbEntity = springDataMysqlHashtagRepository.findByName(hashtag.getName());
    HashtagDbEntity hashtagDbEntity;
    if (optionalHashtagDbEntity.isPresent()) {
      hashtagDbEntity = optionalHashtagDbEntity.get();
      hashtagDbEntity.incrementRepetitions();
    } else {
      hashtagDbEntity = new HashtagDbEntity(hashtag.getName());
    }
    springDataMysqlHashtagRepository.save(hashtagDbEntity);
  }

  @Override
  public List<Hashtag> findAllOrderByRepetitions() {
    return hashtagMapper.mapFromHashtagEntityList(springDataMysqlHashtagRepository.findAllByOrderByRepetitionsDesc());
  }
}
