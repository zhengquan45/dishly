package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product") // 对应数据库表名
@FieldNameConstants
public class Product {

    @TableId
    private Long id;                // 商品ID

    private String name;            // 商品名称

    private String subName;         // 商品副标题

    private String imageUrl;        // 商品图片URL

    private BigDecimal price;       // 商品价格

    private String category;        // 商品类别

    private Boolean isAvailable;    // 是否可用（true: 可用, false: 不可用）

    @TableField(fill = FieldFill.INSERT)  // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 自动填充字段，插入和更新时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间
}