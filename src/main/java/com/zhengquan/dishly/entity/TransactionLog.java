package com.zhengquan.dishly.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 流水记录实体类
 */
@Data
@Accessors(chain = true)
public class TransactionLog {

    private Long id;                 // 流水 ID
    private Long orderId;            // 订单 ID
    private Long userId;             // 用户 ID
    private String transactionType;  // 流水类型 (CARD 或 CASH)
    private Double amount;           // 流水金额，正数表示收入，负数表示支出
    private String status;           // 流水状态 (SUCCESS, PENDING, FAILED)
    private String remark;           // 备注信息
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}