package com.zhengquan.dishly.entity.ro;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductRequest {

    private Long id;                // 商品ID

    private String name;            // 商品名称

    private String subName;         // 商品副标题

    private String imageUrl;        // 商品图片URL

    private BigDecimal price;       // 商品价格

    private String category;        // 商品类别

    private Boolean isAvailable;    // 是否可用（true: 可用, false: 不可用）


}
