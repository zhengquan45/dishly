package com.zhengquan.dishly.demos.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderVo {

    private Long id; // 订单ID
    private Long userId; // 用户名称
    private String deliveryAddress; // 用户地址
    private String paymentMethod; // 支付方式 (WEEK_CARD, MONTH_CARD, CASH)
    private BigDecimal totalAmount; // 支付金额
    private String status; //订单状态
    private LocalDateTime createdAt; // 订单创建时间
    private LocalDateTime updatedAt; // 订单更新时间
}
