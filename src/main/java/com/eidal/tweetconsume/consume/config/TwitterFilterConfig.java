package com.eidal.tweetconsume.consume.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitter.tweets")
@Getter
@Setter
public class TwitterFilterConfig {

  private Integer minFollowers = 1500;
  private String[] languages = {"es", "fr","it"};
  private String[] terms = {"coronavirus"};
}
