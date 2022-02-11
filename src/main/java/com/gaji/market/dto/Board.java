package com.gaji.market.dto;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Integer bid;
    private String uid;
    private String title;
    private String content;
    private Short category;
    private Date date;
}
