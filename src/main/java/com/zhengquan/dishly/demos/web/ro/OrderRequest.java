package com.zhengquan.dishly.demos.web;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRequest {

    private String orderId;
    private String productId;

}
