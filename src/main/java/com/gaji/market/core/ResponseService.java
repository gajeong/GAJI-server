package com.gaji.market.core;

import java.util.List;

import com.gaji.market.common.CommonResult;
import com.gaji.market.common.MultiResult;
import com.gaji.market.common.SingleResult;

import org.springframework.stereotype.Service;

/**
 * 응답 코드 및 응답객체
 */
@Service("ResponseService")
public class ResponseService {

  // 응답 코드를 한곳에서 관리
  // 처리 성공은 0 혹은 양수로
  // 처리 실패는 음수로
  public enum CommonResponse {
    SUCCESS(0, "처리 성공")
    , AUTHSUCCESS(10, "인증서 유효")
    , AUTH_MESSAGE_SUCCESS(11, "문자인증 성공")
    , EXIST(20, "데이터가 이미 있습니다.")
    , LINK_EMAIL_EXIST(21, "이미 등록된 정보가 있습니다.!")
    , FAIL(-10, "처리가 실패 했습니다.")
    , ERR(-20, "처리가 실패했습니다.")
    , ERR_PARAM(-21, "파라미터가 잘못됨")
    , ERR_PHONE_NUMBER(-22, "전화번호가 잘못됨")
    , AUTH_MESSAGE_FAIL(-23, "문자인증 실패")
    , MESSAGE_TIMEOUT(-24, "문자인증 시간초과")
    , FAIL_PARAM_VALID(-25, "파라미터 잘못됨 ")
    , LOGINFAIL(-30, "로그인 실패 했습니다.")
    , UNABLE_SCHEDULE(-40, "이미 등록된 일정입니다.")
    , UNABLE_ADMIN_SCHEDULE(-41, "이미 등록된 (관리자)일정입니다.")
    , UNABLE_FIX_SCHEDULE(-42, "예약된 일정이 있습니다.")
    , UNABLE_PROPOSAL_SCHEDULE(-43, "제안된 일정이 있습니다.")
    , NODATA(-50, "데이터가 없습니다.")
    , EMPTY_ID(-51, "ID정보가 비어있습니다.")
    , AUTHFAIL(-60, "로그인 인증서가 유효 하지않습니다.")
    , INACTIVE_MEMBER(-61, "휴면 계정입니다.")
    , CLOSE_MEMBER(-62, "탈퇴한 회원입니다.")
    , ERR_FILE_UPLOAD(-70, "파일 업로드 실패했습니다.")
    , FILE_SIZE_EXCEEDED(-71, "파일 용량 초과했습니다.")
    , NOT_ALLOW_FILE(-72, "허용되지 않는 파일 입니다.")
    , NO_FILE(-73, "파일이 없습니다.")
    , ALREADY_PAID(-80, "결제된 정보가 있습니다.");

    int code;
    String msg;

    CommonResponse(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }

  }

  public <T> SingleResult<T> getSingleSuccessType(CommonResponse type) {
    SingleResult<T> result = new SingleResult<>();
    result.setSuccess(true);
    result.setCode(type.getCode());
    result.setMsg(type.getMsg());
    return result;
  }

  public <T> SingleResult<T> getSingleFailType(CommonResponse error) {
    SingleResult<T> result = new SingleResult<>();
    result.setSuccess(false);
    result.setCode(error.getCode());
    result.setMsg(error.getMsg());
    return result;
  }

  /**
   * 메세지가 따로 존재하는 경우 사용하는 실패 응답
   */
  public <T> SingleResult<T> getSingleFailType(CommonResponse error, String msg) {
    SingleResult<T> result = new SingleResult<>();
    result.setSuccess(false);
    result.setCode(error.getCode());
    result.setMsg(msg);
    return result;
  }

  public <T> MultiResult<T> getMultiFailType(CommonResponse error) {
    MultiResult<T> result = new MultiResult<>();
    result.setSuccess(false);
    result.setCode(error.getCode());
    result.setMsg(error.getMsg());
    return result;
  }

  public <T> SingleResult<T> getSingleResult(T data) {
    SingleResult<T> result = new SingleResult<>();
    result.setData(data);
    setSuccessResult(result);
    return result;
  }

  public <T> MultiResult<T> getMultiResult(List<T> list) {
    MultiResult<T> result = new MultiResult<>();
    result.setList(list);
    setSuccessResult(result);
    return result;
  }

  public CommonResult getSuccessResult() {
    CommonResult result = new CommonResult();
    setSuccessResult(result);
    return result;
  }


  private void setSuccessResult(CommonResult result) {
    result.setSuccess(true);
    result.setCode(CommonResponse.SUCCESS.getCode());
    result.setMsg(CommonResponse.SUCCESS.getMsg());
  }
}
