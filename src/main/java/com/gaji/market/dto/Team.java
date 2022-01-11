package com.gaji.market.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Team {
    private short TeamNo;
    private String TeamNm;
    private String ShortNm;
    private byte TeamLv;
    private Short UpperTeamNo;
    private Date StartDay;
    private Short ReferTeamNo;
    private char IsUse;
    private char IsLeaf;
    private String TeamMemo;
}
