package com.zhengquan.dishly.demos.web.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderRequest {

    private String openId;
    private String paymentMethod;
    private List<OrderDetailRequest> orderDetailRequestList;

}
