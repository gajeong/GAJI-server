package com.gaji.market.service;

import java.util.List;

import javax.annotation.Resource;

import com.gaji.market.dto.User;
import com.gaji.market.mapper.UserMapper;

import org.springframework.stereotype.Repository;

@Repository
public class UserService implements UserMapper{
    
    @Resource(name="userMapper")
    UserMapper mapper;
    
    @Override
    public void create(User t) throws Exception {
        mapper.create(t);
    }

    @Override
    public List<User> findAll() throws Exception {
        //listAll()의 메소드명과 mapper.xml과 id는 동일해야한다.
        return mapper.findAll();
    }

    @Override
    public User read(String uid) throws Exception {
        return mapper.read(uid);
    }

    @Override
    public void update(User t) throws Exception {
        mapper.update(t);
    }

    @Override
    public void delete(String uid) throws Exception {
        mapper.delete(uid);
    }

    @Override
    public User findById(String uid) throws Exception {
        return mapper.findById(uid);
    }

    @Override
    public User findByNickname(String nickname) throws Exception {
        return mapper.findByNickname(nickname);
    }

}
