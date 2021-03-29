package com.eidal.tweetconsume.core.infraestructure.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationResponse {

  @ApiModelProperty(notes = "Location's longitude", example = "0.23456")
  private Double longitude;

  @ApiModelProperty(notes = "Location's latitude", example = "-0.23456")
  private Double latitude;

}
