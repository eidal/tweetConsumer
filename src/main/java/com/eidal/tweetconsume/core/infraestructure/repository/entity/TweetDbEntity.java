package com.eidal.tweetconsume.core.infraestructure.repository.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TweetDbEntity {

  @Id
  private UUID id;
  @Column(nullable = false)
  private String user;
  @Column(nullable = false, columnDefinition = "text")
  private String text;
  private String location;
  @Column(nullable = false, columnDefinition = "boolean default false")
  private boolean validated;
}
