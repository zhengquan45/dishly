package com.zhengquan.dishly.demos.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountVo {

    private String status;
    private String type;
    private String currentAuthority;
}
