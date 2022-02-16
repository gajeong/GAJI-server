package com.gaji.market.controller;

import java.util.List;

import javax.validation.Valid;

import com.gaji.market.common.CommonResult;
import com.gaji.market.common.MultiResult;
import com.gaji.market.core.ResponseService;
import com.gaji.market.core.ResponseService.CommonResponse;
import com.gaji.market.dto.User;
import com.gaji.market.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
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

@Slf4j  // 로그기능 활성화
@Api(tags = {"1. User"}) // SWAGGER 설정
@RequiredArgsConstructor
@RestController // REST컨트롤러 설정
@RequestMapping("/user") // 최상단 API 경로 설정
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @ApiOperation(value = "User 리스트 전체 조회", notes = "User 리스트 전체 조회")   // SWAGGER 문서 설정
    @GetMapping("/findAll") // GET HTTP 메서드, API 경로 설정
    public ResponseEntity<?> findAll() {

        // GET HTTP 메서드인 경우 결과값을 리턴해주기때문에 MultiResult or SingleResult로 담아서 리턴
        MultiResult<User> result = null;

        try {
            // 서비스를 통해 전체 조회
            List<User> findUser = userService.findAll();

            // 조회한 결과값이 1개 이상인 경우 결과출력
            if(findUser.size()>0)
                result = responseService.getMultiResult(findUser);
            else    // 없는 경우 NODATA로 응답
                result = responseService.getMultiFailType(CommonResponse.NODATA); // 데이터 없음으로 응답, CommonResponse에 사전에 선언된 결과값이 있음

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getMultiFailType(ResponseService.CommonResponse.ERR);  // 예외 발생시 에러로 응답
        }

        // 해당 결과값을 API 응답으로 리턴
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "User 개별 등록", notes = "User 개별 등록")
    @PutMapping("/")    // PUT HTTP 메서드
    public ResponseEntity<?> create(@Valid @RequestBody User user) {

        // PUT, POST, DELETE HTTP 메서드는 데이터 응답이 아닌 결과만 알려주면 되므로 CommonResult로 리턴
        CommonResult result = null;

        try {

            // 계정이 비어있는지 확인
            if(user.getUid()!=null)
            {
                // 추가하는 계정이 존재하는지 확인하기 위해 조회
                User readUser = userService.read(user.getUid());
                boolean nickCheck = false;

                if(8 > user.getNickname().length())
                {
                    nickCheck = true;
                }

                if(readUser!=null)
                {
                    // 계정이 존재하는 경우
                    result = responseService.getSingleFailType(CommonResponse.EXIST);   // 기존에 등록된 정보가 있음으로 응답
                }
                else
                {
                    if(nickCheck == true)
                    {
                        userService.create(user);
    
                        result = responseService.getSuccessResult();
                    }
                    else    
                    {
                        result = responseService.getSingleFailType(CommonResponse.EXIST);
                    }                   
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
    

    @ApiOperation(value = "User 개별 수정", notes = "User 개별 수정")
    @PostMapping("/")   // POST HTTP 메서드
    public ResponseEntity<?> update(@Valid @RequestBody User user) {

        CommonResult result = null;

        try {
            
            if(user.getUid()!=null)
            {
                User readUser = userService.read(user.getUid());

                if(readUser!=null)
                {
                    userService.update(user);

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


    @ApiOperation(value = "User 개별 삭제", notes = "User 개별 삭제")
    @DeleteMapping("/{uid}")  // DELETE HTTP 메서드
    public ResponseEntity<?> delete(@ApiParam(value = "계정 ID", required = true) @PathVariable("uid") String uid) {
        CommonResult result = null;
        try {

            // 계정이 존재하는지 확인
            User user = userService.read(uid);

            if(user!=null)
            {
                // 계정이 존재하는 경우 삭제
                userService.delete(uid);

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
