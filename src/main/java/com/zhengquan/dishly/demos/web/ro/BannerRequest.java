package com.zhengquan.dishly.demos.web.ro;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.DateTimeStrToLocalDateTimeSerializer;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import com.zhengquan.dishly.entity.Banner;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class BannerRequest {

    private Long id;

    private String name;

    private Banner.BannerType type;

    private Integer priority;

    private Banner.ContentType contentType;

    private String contentUrl;

    private String clickUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] dateRange;


}
