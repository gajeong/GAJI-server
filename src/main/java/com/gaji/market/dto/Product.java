package com.gaji.market.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @NotNull(message = "id는 필수 값입니다.")
    private String pid;
    
	@PositiveOrZero
    private String uid;
    private int deposit;
    private int cost;
    private String desc;
    private String title;
    private int status;
    private Date start_date;
    private Date end_date;
}
