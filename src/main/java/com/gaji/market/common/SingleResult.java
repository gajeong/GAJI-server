package com.gaji.market.common;

import lombok.Getter;
import lombok.Setter;

/**
 * API 응답시 결과값이 하나인 경우 사용됨
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
  private T data;
}
