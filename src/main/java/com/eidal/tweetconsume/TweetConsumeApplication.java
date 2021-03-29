package com.eidal.tweetconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TweetConsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetConsumeApplication.class, args);
	}

}
