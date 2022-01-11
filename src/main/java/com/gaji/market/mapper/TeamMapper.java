package com.gaji.market.mapper;

import com.gaji.market.core.IMapper;
import com.gaji.market.core.MapperInterface;
import com.gaji.market.dto.Team;

@MapperInterface
public interface TeamMapper extends IMapper<Team>{
    public void deleteByTeamNo (short teamNo) throws Exception;
    public Team readByTeamNo (short teamNo) throws Exception;
}
