package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_address")  // 映射数据库表名
public class UserAddress {

    @TableId  // 表示主键
    private Long id;                      // 唯一ID
    private Long userId;                  // 用户ID，关联用户表
    private String communityName;         // 小区名称
    private String streetAddress;         // 街道和门牌号
    private String recipientName;         // 收货人姓名
    private String phoneNumber;           // 收货人手机号
    private AddressLabel addressLabel;    // 地址标签
    private String remark;                // 地址备注
    private Boolean isDefault;            // 是否为默认地址
    private LocalDateTime createdAt;      // 创建时间
    private LocalDateTime updatedAt;      // 更新时间

    // 枚举：地址标签
    public enum AddressLabel {
        HOME,
        WORK,
        FRIEND
    }
}
