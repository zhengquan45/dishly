package com.zhengquan.dishly.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LocalDateTimeToTimestampSerializer;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;


@FieldNameConstants
@TableName("admin_user")
@Data
public class AdminUser {

    // 管理员ID
    private Long id;

    // 管理员用户名
    private String username;

    // 加密后的登录密码
    private String password;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 邮箱地址
    private String email;

    // 手机号
    private String phone;

    // 是否为超级管理员（1=是，0=否）
    private Boolean isSuperAdmin;

    // 状态（1=正常，0=禁用）
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)  // 自动填充字段，插入时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 自动填充字段，插入和更新时自动填充
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime updatedAt;  // 更新时间

}

 
