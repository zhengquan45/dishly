package com.zhengquan.dishly.demos.web.ro;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductRequest {


    @TableId
    private Long id;                // 商品ID

    private String name;            // 商品名称

    private String imageUrl;        // 商品图片URL

    private BigDecimal price;       // 商品价格

    private String category;        // 商品类别

    private Boolean isAvailable;    // 是否可用（true: 可用, false: 不可用）


}
