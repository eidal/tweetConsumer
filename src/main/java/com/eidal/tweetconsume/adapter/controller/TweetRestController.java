package com.eidal.tweetconsume.adapter.controller;

import com.eidal.tweetconsume.adapter.service.TweetService;
import com.eidal.tweetconsume.adapter.utils.TweetAdapterConsts;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.TweetResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TweetAdapterConsts.TWEET_REST_V1 + "/tweets")
@Api(value = "Tweets Controller API REST")
@AllArgsConstructor
public class TweetRestController {

  private final TweetService tweetService;

  @ApiOperation(value = "Get tweet lists", response = List.class)
  @ApiResponses(value={
          @ApiResponse(code=200,message="Tweets Detail Retrieved",response=List.class),
          @ApiResponse(code=500,message="Internal Server Error")
  })
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<TweetResponse>> getTweets() {
    return new ResponseEntity<>(tweetService.getTweets(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get validated tweets by user", response = List.class)
  @ApiResponses(value={
          @ApiResponse(code=200,message="Competition Details Retrieved",response=List.class),
          @ApiResponse(code=400,message="Bad Request"),
          @ApiResponse(code=404,message="User Not Found"),
          @ApiResponse(code=500,message="Internal Server Error")
  })
  @GetMapping(value = "/users/{userId}", produces = "application/json")
  public ResponseEntity<List<TweetResponse>> getValidatedTweetsByUser(@ApiParam(value = "the tweets user's identification", required = true)
                                                                      @NotBlank @PathVariable(value = "userId") String user) {
    return new ResponseEntity<>(tweetService.getValidatedTweetsByUser(user), HttpStatus.OK);
  }

  @ApiOperation(value = "Validate tweet by Id")
  @ApiResponses(value={
          @ApiResponse(code=204,message="Tweet validated"),
          @ApiResponse(code=400,message="Bad Request"),
          @ApiResponse(code=404,message="Tweet Not Found"),
          @ApiResponse(code=500,message="Internal Server Error")
  })
  @PatchMapping(value = "/{tweetId}/validate", produces = "application/json")
  public ResponseEntity<Void> validateTweetById(@ApiParam(value = "the tweet's identification", required = true)
                                                @NotNull @PathVariable(value = "tweetId") UUID tweetId) {
    tweetService.validateTweet(tweetId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
