package com.gaji.market.core;

import java.util.List;

/**
 * Mybatis에 사용
 * 모든 DB조회에 기본적으로 사용되므로 인터페이스로 선언
 * 모든 MAPPER에 상속됨
 * <T> 제너릭 - 내부에서 타입을 지정하는게 아닌 밖에서 지정할수 있게 함
 */
public interface IMapper<T> {
    public void create(T t) throws Exception;

    public List<T> findAll() throws Exception;
    
    public T read(String id) throws Exception;

    public void update (T t) throws Exception;

    public void delete (String id) throws Exception;
}
