package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("banner")
@Data
public class Banner {

    @TableId
    private Long id;

    private String name;

    private BannerType type;

    private Integer priority;

    private ContentType contentType;

    private String contentUrl;

    private String clickUrl;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean status = true;

    @TableField(fill = FieldFill.INSERT)  // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 自动填充字段，插入和更新时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间

    // Enum for BannerType
    public enum BannerType {
        BANNER, INTERSTITIAL, NATIVE, REWARDED_VIDEO, FLOATING, RECOMMENDATION
    }

    // Enum for ContentType
    public enum ContentType {
        IMAGE, VIDEO, HTML
    }
}
