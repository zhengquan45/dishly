package com.zhengquan.dishly.entity.ro;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 卡模板实体类
 */
@Data
@Accessors(chain = true)
public class CardTemplateRequest {

    private String cardName;   // 卡名称
    private Integer totalUses; // 卡总次数
    private Integer validDays; // 卡有效天数

}