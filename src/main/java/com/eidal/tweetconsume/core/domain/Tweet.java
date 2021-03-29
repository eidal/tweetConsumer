package com.eidal.tweetconsume.core.domain;

import com.eidal.tweetconsume.core.domain.exception.TweetException;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class Tweet {

  private UUID id;
  private String user;
  private String text;
  private Location location;
  private boolean validated;

  public Tweet(String user, String text, Location location) {
    if (Objects.nonNull(user) && Objects.nonNull(text)) {
      this.id = UUID.randomUUID();
      this.user = user;
      this.text = text;
      this.location = location;
      this.validated = false;
    } else {
      throw new TweetException("Error! Invalid tweet information");
    }
  }
}
