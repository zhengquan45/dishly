package com.zhengquan.dishly.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocationVo {

    private String name; // 小区名称

    private BigDecimal longitude; // 经度

    private BigDecimal latitude; // 纬度

    private String description; // 位置描述
}
