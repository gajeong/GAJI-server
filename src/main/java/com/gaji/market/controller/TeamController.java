package com.gaji.market.controller;

import java.util.List;

import javax.validation.Valid;

import com.gaji.market.common.CommonResult;
import com.gaji.market.common.MultiResult;
import com.gaji.market.core.ResponseService;
import com.gaji.market.core.ResponseService.CommonResponse;
import com.gaji.market.dto.Team;
import com.gaji.market.service.TeamService;

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


@Slf4j
@Api(tags = {"2. Team"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {

    private final ResponseService responseService;
    private final TeamService teamService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        MultiResult<Team> result = null;

        try {
            List<Team> findTeam = teamService.findAll();

            if(findTeam.size()>0)
                result = responseService.getMultiResult(findTeam);
            else
                result = responseService.getMultiFailType(CommonResponse.NODATA);

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getMultiFailType(ResponseService.CommonResponse.ERR);
        }
        
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Team 개별 등록", notes = "Team 개별 등록")
    @PutMapping("/")    // PUT HTTP 메서드
    public ResponseEntity<?> create(@Valid @RequestBody Team team) {

        // PUT, POST, DELETE HTTP 메서드는 데이터 응답이 아닌 결과만 알려주면 되므로 CommonResult로 리턴
        CommonResult result = null;

        try {
            
            // 팀명이 비어있는지 확인
            if(team.getTeamNo()!=0)
            {
                teamService.create(team);

                result = responseService.getSuccessResult();
            }
            else
            {
                // 팀명이 비어있는경우
                result = responseService.getSingleFailType(CommonResponse.ERR_PARAM);    // 빈계정 알림
            }

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getSingleFailType(CommonResponse.ERR);
        }

        return ResponseEntity.ok().body(result);
    }
    

    @ApiOperation(value = "Team 개별 수정", notes = "Team 개별 수정")
    @PostMapping("/")   // POST HTTP 메서드
    public ResponseEntity<?> update(@Valid @RequestBody Team team) {

        CommonResult result = null;

        try {
            
            if(team.getTeamNo()>=0)
            {
                Team readTeam = teamService.readByTeamNo(team.getTeamNo());

                if(readTeam!=null)
                {
                    teamService.update(team);

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



    @ApiOperation(value = "Team 개별 삭제", notes = "Team 개별 삭제")
    @DeleteMapping("/{teamNo}")  // DELETE HTTP 메서드
    public ResponseEntity<?> delete(@ApiParam(value = "팀 번호", required = true) @PathVariable("teamNo") short teamNo) {
        CommonResult result = null;
        try {

            // 팀이 존재하는지 확인
            Team team = teamService.readByTeamNo(teamNo);

            if(team!=null)
            {
                // 팀이 존재하는 경우 삭제
                teamService.deleteByTeamNo(teamNo);

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
