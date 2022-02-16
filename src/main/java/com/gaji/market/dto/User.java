package com.gaji.market.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class User {
    
    @NotNull(message = "id는 필수입니다.")
    private String uid;
	private String pw;
    private String nickname;
    private Date birth;
    private String phone;
    private float manner;
    private String intro;
}
