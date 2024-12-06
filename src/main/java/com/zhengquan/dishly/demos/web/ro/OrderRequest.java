package com.zhengquan.dishly.demos.web.ro;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRequest {

    private String openId;
    private String paymentMethod;
    private String productId;

}
