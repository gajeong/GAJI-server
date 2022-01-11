package com.gaji.market.core;

import com.gaji.market.common.CommonResult;
import com.gaji.market.core.ResponseService.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * AOP를 활용한 예외처리
 * 컨트롤러에서 발생하는 예외를 가로채서 원하는 응답으로 리턴
 * 현재는 VALID용도로 사용중
 * 요청할때 전달하는 객체를 검증시 문제가 있는경우 자세한정보로 리턴
 */
@RestControllerAdvice
public class ValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)    // 예외처리할 Exception객체 설정
    @ResponseStatus()
    public ResponseEntity<?> handleMethodArgumentException(MethodArgumentNotValidException e) {   
        CommonResult result = null;
        ResponseService service = new ResponseService();
        StringBuilder builder = new StringBuilder();
        // 예외 정보에서 검증에 문제가 발생한 정보를 상세하게 전달
        for (FieldError fieldError : e.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
            builder.append(System.lineSeparator());
        }
        
        result = service.getSingleFailType(CommonResponse.FAIL_PARAM_VALID, builder.toString());    // 파라미터 문제로 응답
        return ResponseEntity.ok().body(result);
    }
}
