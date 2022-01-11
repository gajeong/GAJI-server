package com.gaji.market.common;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * API 응답시 데이터가 다수인 경우 사용
 */
@Getter
@Setter
public class MultiResult<T> extends CommonResult {
  private List<T> list;
}
