package com.eidal.tweetconsume.core.infraestructure.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class TweetResponse implements Serializable {
  @ApiModelProperty(notes = "Tweet's identification", example = "06356c62-9884-4b2c-9ce1-8ea6de8b8adb")
  private UUID id;

  @ApiModelProperty(notes = "Tweet's user identification", example = "123456789", position = 1)
  private String user;

  @ApiModelProperty(notes = "Tweet's text", example = "This is my first tweet!", position = 2)
  private String text;

  @ApiModelProperty(notes = "Tweet's location", position = 3)
  private LocationResponse location;

  @ApiModelProperty(notes = "Indicate tweet is or not validated", example = "true", position = 4)
  private boolean validated;
}


