package com.eidal.tweetconsume.core.infraestructure.mapper;

import com.eidal.tweetconsume.core.domain.Location;
import com.eidal.tweetconsume.core.domain.Tweet;
import com.eidal.tweetconsume.core.domain.exception.TweetException;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.LocationResponse;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.TweetResponse;
import com.eidal.tweetconsume.core.infraestructure.repository.entity.TweetDbEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.User;

@Component
public class TweetMapper {

  public Tweet mapFromStatusTwitter(Status status) {
    String user = Optional.ofNullable(status).map(Status::getUser).map(User::getId).map(String::valueOf)
            .orElseThrow(() -> new TweetException("Error! The tweet user is null"));
    String text = Optional.ofNullable(status).map(Status::getText)
            .orElseThrow(() -> new TweetException("Error! The tweet id is null"));
    Location location = Optional.ofNullable(status).map(Status::getGeoLocation)
            .map(loc -> new Location(loc.getLongitude(), loc.getLatitude())).orElse(null);
    return new Tweet(user, text, location);
  }

  public TweetDbEntity mapToTweetEntity(Tweet tweet) {
    ObjectMapper mapper = new ObjectMapper();
    String location = Optional.ofNullable(tweet).map(Tweet::getLocation)
            .map(loc -> {
              try {
                return mapper.writeValueAsString(loc);
              } catch (JsonProcessingException e) {
                return null;
              }
            }).orElse(null);
    TweetDbEntity tweetDbEntity = new TweetDbEntity();
    tweetDbEntity.setId(tweet.getId());
    tweetDbEntity.setUser(tweet.getUser());
    tweetDbEntity.setText(tweet.getText());
    tweetDbEntity.setLocation(location);
    tweetDbEntity.setValidated(tweet.isValidated());
    return tweetDbEntity;
  }

  public Tweet mapFromTweetEntity(TweetDbEntity tweetDbEntity) {
    ObjectMapper mapper = new ObjectMapper();
    Location location = Optional.ofNullable(tweetDbEntity.getLocation())
            .map(loc -> {
              try {
                return mapper.convertValue(loc, Location.class);
              } catch (IllegalArgumentException e) {
                return null;
              }
            }).orElse(null);
    Tweet tweet= new Tweet(tweetDbEntity.getUser(), tweetDbEntity.getText(), location);
    tweet.setId(tweetDbEntity.getId());
    tweet.setValidated(tweetDbEntity.isValidated());
    return tweet;
  }

  public List<Tweet> mapFromTweetEntityToList(Collection<TweetDbEntity> tweetEntities) {
    return tweetEntities.stream().map(this::mapFromTweetEntity).collect(Collectors.toList());
  }

  public TweetResponse mapToTweetRestResponse(Tweet tweet) {
    TweetResponse response = new TweetResponse();
    response.setId(tweet.getId());
    response.setUser(tweet.getUser());
    response.setText(tweet.getText());
    response.setLocation(Optional.ofNullable(tweet.getLocation())
            .map(location -> new LocationResponse(location.getLongitude(), location.getLatitude())).orElse(null));
    response.setValidated(tweet.isValidated());
    return response;
  }

  public List<TweetResponse> mapToTweetRestResponseList(List<Tweet> tweets) {
    return tweets.stream().map(this::mapToTweetRestResponse).collect(Collectors.toList());
  }
}
