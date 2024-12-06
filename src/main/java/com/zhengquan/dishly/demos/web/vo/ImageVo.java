package com.zhengquan.dishly.demos.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageVo {
    private String url;
}
