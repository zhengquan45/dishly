package com.zhengquan.dishly.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderDetail {
    private Long id;
    private Long orderId; //订单id
    private Long productId; //商品id
    private Integer productNum; //商品数量
    private LocalDateTime createdAt; // 订单创建时间
    private LocalDateTime updatedAt; // 订单更新时间

}
