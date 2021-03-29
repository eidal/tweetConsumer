package com.eidal.tweetconsume.core.infraestructure.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HashtagResponse {

  @ApiModelProperty(notes = "Hashtag's name", example = "Sobreviviré")
  private String name;
}
