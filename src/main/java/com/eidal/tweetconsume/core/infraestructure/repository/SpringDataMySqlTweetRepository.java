package com.eidal.tweetconsume.core.infraestructure.repository;

import com.eidal.tweetconsume.core.infraestructure.repository.entity.TweetDbEntity;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMySqlTweetRepository extends JpaRepository<TweetDbEntity, UUID> {

  Collection<TweetDbEntity> findByUserAndValidatedIsTrue(String user);
}
