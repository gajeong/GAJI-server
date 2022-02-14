package com.gaji.market.controller;

import java.util.List;

import javax.validation.Valid;

import com.gaji.market.common.CommonResult;
import com.gaji.market.common.MultiResult;
import com.gaji.market.core.ResponseService;
import com.gaji.market.core.ResponseService.CommonResponse;
import com.gaji.market.dto.Board;
import com.gaji.market.service.BoardService;

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
@Api(tags = {"4. Board"}) 
@RequiredArgsConstructor
@RestController 
@RequestMapping("/board") 
public class BoardController {

    private final ResponseService responseService;
    private final BoardService boardService;

    @ApiOperation(value = "Board 리스트 전체 조회", notes = "Board 리스트 전체 조회") 
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        MultiResult<Board> result = null;

        try {
            List<Board> findBoard = boardService.findAll();
            if(findBoard.size()>0)
                result = responseService.getMultiResult(findBoard);
            else   
                result = responseService.getMultiFailType(CommonResponse.NODATA); 

        } catch (Exception e) {
            log.error("처리중 예외 : " + e.getMessage());
            result = responseService.getMultiFailType(ResponseService.CommonResponse.ERR);  
        }
        return ResponseEntity.ok().body(result);
    }
    
    @ApiOperation(value = "Board 개별 등록", notes = "Board 개별 등록")
    @PutMapping("/")    // PUT HTTP 메서드
    public ResponseEntity<?> create(@Valid @RequestBody Board board) {

        CommonResult result = null;

        try {
            
            if(board.getBid()!=0)
            {
                Board readBoard = boardService.read(board.getBid());

                if(readBoard!=null)
                {
                    result = responseService.getSingleFailType(CommonResponse.EXIST);  
                }
                else
                {
                  boardService.create(board);
    
                    result = responseService.getSuccessResult();                    
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
    

    @ApiOperation(value = "Board 개별 수정", notes = "Board 개별 수정")
    @PostMapping("/")  
    public ResponseEntity<?> update(@Valid @RequestBody Board board) {

        CommonResult result = null;

        try {
            
            if(board.getBid()!=0)
            {
                Board readBoard = boardService.read(board.getBid());

                if(readBoard!=null)
                {
                  boardService.update(board);

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



    @ApiOperation(value = "Board 개별 삭제", notes = "Board 개별 삭제")
    @DeleteMapping("/{bid}") 
    public ResponseEntity<?> delete(@ApiParam(value = "게시판 ID", required = true) @PathVariable("bid") int bid) {
        CommonResult result = null;
        try {

            Board board = boardService.read(bid);

            if(board!=null)
            {
                boardService.delete(bid);

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
