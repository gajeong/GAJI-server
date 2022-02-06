package com.gaji.market.dto;
import java.util.Date;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Board {
  @NotNull(message = "id는 필수 값입니다.")
    private String bid;
    private String uid;
    private String title;
    private String content;
    private String category;
    private Date date;
}
