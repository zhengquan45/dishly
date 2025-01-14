package com.zhengquan.dishly.entity.ro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhengquan.dishly.entity.Banner;
import lombok.Data;

import java.time.LocalDateTime;


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

    private Boolean status;


}
