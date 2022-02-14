package com.gaji.market.mapper;

import com.gaji.market.core.IMapper;
import com.gaji.market.core.MapperInterface;
import com.gaji.market.dto.Board;
@MapperInterface
public interface BoardMapper extends IMapper<Board>{
  public Board read (int id) throws Exception;
  public Board findByUid (String uid) throws Exception;
  public void delete (int bid) throws Exception;
}