package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("user")  // 映射数据库表名
public class User {

    @TableId  // 表示主键
    private Long id;  // 用户ID

    private String openId;  // 微信OpenId（唯一标识）

    private String nickname;  // 用户昵称

    private String avatarUrl;  // 用户头像URL

    private String deliveryAddress;  // 送餐地址

    private String contactName;  // 收件人姓名

    private String contactPhone;  // 收件人电话

    private Integer gender = 0;  // 用户性别（0: 未知，1: 男，2: 女）

    @TableField(fill = FieldFill.INSERT)  // 自动填充字段，插入时自动填充
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 自动填充字段，插入和更新时自动填充
    private LocalDateTime updatedAt;  // 更新时间
}
