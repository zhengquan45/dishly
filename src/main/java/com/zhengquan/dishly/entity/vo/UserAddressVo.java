package com.zhengquan.dishly.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import com.zhengquan.dishly.config.LongToStrSerializer;
import com.zhengquan.dishly.entity.UserAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserAddressVo {
    @JsonSerialize(using = LongToStrSerializer.class)
    private Long id;

    private String communityName;         // 小区名称

    private String streetAddress;         // 街道和门牌号

    private String recipientName;         // 收货人姓名

    private String phoneNumber;           // 收货人手机号

    private UserAddress.AddressLabel addressLabel;    // 地址标签

    private String remark;                // 地址备注

    private Boolean isDefault;            // 是否为默认地址

    // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间
}
