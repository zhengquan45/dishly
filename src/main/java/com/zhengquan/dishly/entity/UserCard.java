package com.zhengquan.dishly.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户卡券实体类
 */
@Data
public class UserCard {
    private Long id;                 // 卡券 ID
    private Long userId;             // 用户 ID
    private Long templateId;         // 来源模板 ID
    private BigDecimal price;        // 卡券价格
    private Integer remainingUses;   // 剩余次数
    private LocalDate startDate;     // 激活日期
    private LocalDate endDate;       // 到期日期
    private String status;           // 卡券状态：ACTIVE, EXPIRED, USED
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
