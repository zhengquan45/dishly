package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@FieldNameConstants
@TableName("location")
@Data
public class Location {

    private Integer id; // 主键，自动增长

    private String locationId; // 小区唯一标识符

    private String name; // 小区名称

    private BigDecimal longitude; // 经度

    private BigDecimal latitude; // 纬度

    private String description; // 位置描述

    @TableField(fill = FieldFill.INSERT)  // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 自动填充字段，插入和更新时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间

}

 
