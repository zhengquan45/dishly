package com.zhengquan.dishly.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Order {

    private Long id; // 订单ID
    private String userName; // 用户名称
    private String userAddress; // 用户地址
    private String userPhone; // 用户电话
    private Long productId; // 商品ID
    private String paymentMethod; // 支付方式 (WEEK_CARD, MONTH_CARD, CASH)
    private BigDecimal totalAmount; // 支付金额
    private String status; //订单状态
    private LocalDateTime createdAt; // 订单创建时间
    private LocalDateTime updatedAt; // 订单更新时间
}