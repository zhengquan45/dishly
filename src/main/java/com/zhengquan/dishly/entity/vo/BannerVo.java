package com.zhengquan.dishly.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import com.zhengquan.dishly.config.LongToStrSerializer;
import com.zhengquan.dishly.entity.Banner;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BannerVo {

    @JsonSerialize(using = LongToStrSerializer.class)
    private Long id;

    private String name;

    private Banner.BannerType type;

    private Integer priority;

    private Banner.ContentType contentType;

    private String contentUrl;

    private String clickUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] dateRange;

    private Boolean status;
    // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间
}
