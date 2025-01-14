package com.zhengquan.dishly.entity.ro;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PayOrderRequest {

    private Long orderId;

    private Long userId;

}
