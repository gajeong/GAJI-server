package com.gaji.market.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * API 응답시 공통적으로 쓰이는 결과객체
 */
@Getter
@Setter
public class CommonResult {

  @ApiModelProperty(value = "true:성공,false:실패")
  private boolean success;

  @ApiModelProperty(value = " 결과코드 0:정상, 결과코드 < 0 : 비정상")
  private int code;

  @ApiModelProperty(value = "응답결과 메시지")
  private String msg;

}
