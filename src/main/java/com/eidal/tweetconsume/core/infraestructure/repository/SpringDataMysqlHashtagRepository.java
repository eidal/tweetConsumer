package com.eidal.tweetconsume.core.infraestructure.repository;

import com.eidal.tweetconsume.core.infraestructure.repository.entity.HashtagDbEntity;
import java.util.Collection;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface SpringDataMysqlHashtagRepository extends JpaRepository<HashtagDbEntity, String> {

  Collection<HashtagDbEntity> findAllByOrderByRepetitionsDesc();

  @Lock(LockModeType.PESSIMISTIC_READ)
  Optional<HashtagDbEntity> findByName(String name);
}
