package com.zhengquan.dishly.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<T> {
    private Integer current;
    private Integer pageSize;
    private Long total;
    private List<T> data;
    private Boolean success;
}
