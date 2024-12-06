package com.zhengquan.dishly.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 卡模板实体类
 */
@Data
public class CardTemplate {
    private Long id;                 // 卡模板 ID
    private String cardName;         // 卡名称
    private Integer totalUses;       // 卡总次数
    private Integer validDays;       // 卡有效天数
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
