package com.gaji.market.controller;

import java.util.List;

import javax.validation.Valid;

import com.gaji.market.common.CommonResult;
import com.gaji.market.common.MultiResult;
import com.gaji.market.core.ResponseService;
import com.gaji.market.core.ResponseService.CommonResponse;
import com.gaji.market.dto.Account;
import com.gaji.market.service.AccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러 설정
 * API 호출시 사용되는 최상단 접근부분
 * 실제로 필터, 인터셉터가 더 있으나 현재는 교육중으로 무시
 */
@Slf4j  // 로그기능 활성화
@Api(tags = {"1. Account"}) // SWAGGER 설정
@RequiredArgsConstructor
@RestController // REST컨트롤러 설정
@RequestMapping("/account") // 최상단 API 경로 설정
public class AccountController {

    private final ResponseService responseService;
    private final AccountService accountService;

    @ApiOperation(value = "Account 리스트 전체 조회", notes = "Account 리스트 전체 조회")   // SWAGGER 문서 설정
    @GetMapping("/findAll") // GET HTTP 메서드, API 경로 설정
    public ResponseEntity<?> findAll() {

        // GET HTTP 메서드인 경우 결과값을 리턴해주기때문에 MultiResult or SingleResult로 담아서 리턴
        MultiResult<Account> result = null;

        try {
            // 서비스를 통해 전체 조회
            List<Account> findAccount = accountService.findAll();

            // 조회한 결과값이 1개 이상인 경우 결과출력
            if(findAccount.size()>0)
                result = responseService.getMultiResult(findAccount);
            else    // 없는 경우 NODATA로 응답
                result = responseService.getMultiFailType(CommonResponse.NODATA); // 데이터 없음으로 응답, CommonResponse에 사전에 선언된 결과값이 있음

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getMultiFailType(ResponseService.CommonResponse.ERR);  // 예외 발생시 에러로 응답
        }

        // 해당 결과값을 API 응답으로 리턴
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Account 개별 등록", notes = "Account 개별 등록")
    @PutMapping("/")    // PUT HTTP 메서드
    public ResponseEntity<?> create(@Valid @RequestBody Account account) {

        // PUT, POST, DELETE HTTP 메서드는 데이터 응답이 아닌 결과만 알려주면 되므로 CommonResult로 리턴
        CommonResult result = null;

        try {
            
            // 계정이 비어있는지 확인
            if(account.getAccountId()!=null)
            {
                // 추가하는 계정이 존재하는지 확인하기 위해 조회
                Account readAccount = accountService.read(account.getAccountId());

                if(readAccount!=null)
                {
                    // 계정이 존재하는 경우
                    result = responseService.getSingleFailType(CommonResponse.EXIST);   // 기존에 등록된 정보가 있음으로 응답
                }
                else
                {
                    accountService.create(account);
    
                    result = responseService.getSuccessResult();                    
                }
            }
            else
            {
                // 계정이 비어있는경우
                result = responseService.getSingleFailType(CommonResponse.EMPTY_ID);    // 빈계정 알림
            }

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getSingleFailType(CommonResponse.ERR);
        }

        return ResponseEntity.ok().body(result);
    }
    

    @ApiOperation(value = "Account 개별 수정", notes = "Account 개별 수정")
    @PostMapping("/")   // POST HTTP 메서드
    public ResponseEntity<?> update(@Valid @RequestBody Account account) {

        CommonResult result = null;

        try {
            
            if(account.getAccountId()!=null)
            {
                Account readAccount = accountService.read(account.getAccountId());

                if(readAccount!=null)
                {
                    accountService.update(account);

                    result = responseService.getSuccessResult();
                }else
                {
                    result = responseService.getSingleFailType(CommonResponse.NODATA);
                }
            }
            else
            {
                result = responseService.getSingleFailType(CommonResponse.EMPTY_ID);
            }

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getSingleFailType(CommonResponse.ERR);
        }

        return ResponseEntity.ok().body(result);
    }



    @ApiOperation(value = "Account 개별 삭제", notes = "Account 개별 삭제")
    @DeleteMapping("/{accountId}")  // DELETE HTTP 메서드
    public ResponseEntity<?> delete(@ApiParam(value = "계정 ID", required = true) @PathVariable("accountId") String accountId) {
        CommonResult result = null;
        try {

            // 계정이 존재하는지 확인
            Account account = accountService.read(accountId);

            if(account!=null)
            {
                // 계정이 존재하는 경우 삭제
                accountService.delete(accountId);

                result = responseService.getSingleResult(CommonResponse.SUCCESS);   
            } 
            else
            {
                result = responseService.getSingleFailType(CommonResponse.NODATA);
            }

        }catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getSingleFailType(CommonResponse.ERR);
        }

        return ResponseEntity.ok().body(result);
    }

}
