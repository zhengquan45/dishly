package com.zhengquan.dishly.demos.web.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserCardUpdateRequest {

    private Long userCardId;

    private String status;

    private Integer remainingUses;   // 剩余次数

    private LocalDate endDate;

}
