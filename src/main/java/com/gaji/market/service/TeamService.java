package com.gaji.market.service;

import java.util.List;

import javax.annotation.Resource;

import com.gaji.market.dto.Team;
import com.gaji.market.mapper.TeamMapper;

import org.springframework.stereotype.Repository;

@Repository
public class TeamService implements TeamMapper {

    @Resource(name = "teamMapper")
    TeamMapper mapper;

    @Override
    public void create(Team t) throws Exception {
        mapper.create(t);
    }

    @Override
    public List<Team> findAll() throws Exception {
        // listAll()의 메소드명과 mapper.xml과 id는 동일해야한다.
        return mapper.findAll();
    }

    @Override
    public Team read(String id) throws Exception {
        return mapper.read(id);
    }

    @Override
    public void update(Team t) throws Exception {
        mapper.update(t);
    }

    @Override
    public void delete(String id) throws Exception {
    }

    @Override
    public void deleteByTeamNo(short teamNo) throws Exception {
        mapper.deleteByTeamNo(teamNo);
    }

    @Override
    public Team readByTeamNo(short teamNo) throws Exception {
        return mapper.readByTeamNo(teamNo);
    }

}
