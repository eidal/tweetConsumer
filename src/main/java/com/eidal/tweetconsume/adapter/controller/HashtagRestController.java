package com.eidal.tweetconsume.adapter.controller;

import com.eidal.tweetconsume.adapter.service.HashtagService;
import com.eidal.tweetconsume.adapter.utils.TweetAdapterConsts;
import com.eidal.tweetconsume.core.infraestructure.controller.dto.HashtagResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(TweetAdapterConsts.TWEET_REST_V1 + "/hashtags")
@Api(value = "Hashtags Controller API REST")
public class HashtagRestController {


  private final HashtagService hashtagService;

  @ApiOperation(value = "Get Hashtags more used", response = List.class)
  @ApiResponses(value={
          @ApiResponse(code=200,message="Hashtags Detail Retrieved",response=List.class),
          @ApiResponse(code=500,message="Internal Server Error")
  })
  @GetMapping(value = {"","/{numHashtags}"}, produces = "application/json")
  public ResponseEntity<List<HashtagResponse>> getHashtagsMoreUsed(@ApiParam(value = "the quantity of hashtags requested")
                                                   @PathVariable(required = false, value = "numHashtags")  Long numHashtags) {
    return new ResponseEntity<>(hashtagService.getHashtagsMoreUsed(numHashtags), HttpStatus.OK);
  }

}
