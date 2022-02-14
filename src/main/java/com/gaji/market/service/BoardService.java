package com.gaji.market.service;

import java.util.List;

import javax.annotation.Resource;

import com.gaji.market.dto.Board;
import com.gaji.market.mapper.BoardMapper;

import org.springframework.stereotype.Repository;

/**
 * Mybatis에 사용
 * BoardMapper를 구현
 * Mybatis가 자동으로 매핑한 BoardMapper의 
 */
@Repository
public class BoardService implements BoardMapper{

    @Resource(name="boardMapper")
    BoardMapper mapper;
    
    @Override
    public void create(Board t) throws Exception {
        mapper.create(t);
    }

    @Override
    public List<Board> findAll() throws Exception {
        //listAll()의 메소드명과 mapper.xml과 id는 동일해야한다.
        return mapper.findAll();
    }

    @Override
    public Board read(int bid) throws Exception {
        return mapper.read(bid);
    }

    @Override
    public void update(Board t) throws Exception {
        mapper.update(t);
    }

    @Override
    public void delete(int bid) throws Exception {
        mapper.delete(bid);
    }


    @Override
    public Board read(String id) throws Exception {
      return null;
    }

    @Override
    public Board findByUid(String uid) throws Exception {
      return mapper.findByUid(uid);
    }

    @Override
    public void delete(String bid) throws Exception {
      
    }


    
}
