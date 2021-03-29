package com.eidal.tweetconsume.core.infraestructure.repository.entity;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HashtagDbEntity {
  @Id
  private String name;

  @Column(nullable = false)
  private Integer repetitions;

  public HashtagDbEntity(String name) {
    this.name = name;
    this.repetitions = 1;
  }
  public void incrementRepetitions() {
    int repetitions = Optional.ofNullable(this.repetitions).orElse(1);
    repetitions++;
    this.repetitions = repetitions;
  }
}
