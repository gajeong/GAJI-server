package com.gaji.market.service;

import java.util.List;

import javax.annotation.Resource;

import com.gaji.market.dto.Account;
import com.gaji.market.mapper.AccountMapper;

import org.springframework.stereotype.Repository;

/**
 * Mybatis에 사용
 * AccountMapper를 구현
 * Mybatis가 자동으로 매핑한 AccountMapper의 
 */
@Repository
public class AccountService implements AccountMapper{

    @Resource(name="accountMapper")
    AccountMapper mapper;
    
    @Override
    public void create(Account t) throws Exception {
        mapper.create(t);
    }

    @Override
    public List<Account> findAll() throws Exception {
        //listAll()의 메소드명과 mapper.xml과 id는 동일해야한다.
        return mapper.findAll();
    }

    @Override
    public Account read(String id) throws Exception {
        return mapper.read(id);
    }

    @Override
    public void update(Account t) throws Exception {
        mapper.update(t);
    }

    @Override
    public void delete(String id) throws Exception {
        mapper.delete(id);
    }
    
}
