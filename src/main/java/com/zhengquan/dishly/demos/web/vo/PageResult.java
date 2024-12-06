package com.zhengquan.dishly.demos.web.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<T> {
    private Integer current;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
    private Boolean success;
}
