package com.zhengquan.dishly.entity.ro;

import com.zhengquan.dishly.entity.UserAddress;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAddressRequest {
    private Long id;
    private String communityName;         // 小区名称
    private String streetAddress;         // 街道和门牌号
    private String recipientName;         // 收货人姓名
    private String phoneNumber;           // 收货人手机号
    private UserAddress.AddressLabel addressLabel;    // 地址标签
    private String remark;                // 地址备注
    private Boolean isDefault;            // 是否为默认地址
}
