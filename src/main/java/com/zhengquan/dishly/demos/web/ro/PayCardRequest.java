package com.zhengquan.dishly.demos.web.ro;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PayCardRequest {

    private Long userCardId;

    private Long userId;

}
