package com.zhengquan.dishly.demos.web.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import com.zhengquan.dishly.config.LongToStrSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ProductVo {
    @JsonSerialize(using = LongToStrSerializer.class)
    private Long id;                // 商品ID

    private String name;            // 商品名称

    private String imageUrl;        // 商品图片URL

    private BigDecimal price;       // 商品价格

    private String category;        // 商品类别

    private Boolean isAvailable;    // 是否可用（true: 可用, false: 不可用）
    // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间
}
