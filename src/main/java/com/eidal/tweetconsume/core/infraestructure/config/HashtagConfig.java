package com.eidal.tweetconsume.core.infraestructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitter")
@Getter
@Setter
public class HashtagConfig {

  private Long hashtagsNumClasification = 10L;
}
