package com.zhengquan.dishly.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhengquan.dishly.config.LongToStrSerializer;
import lombok.Data;

@Data
public class AdminUserVo {

    // 管理员ID
    @JsonSerialize(using = LongToStrSerializer.class)
    private Long id;

    // 管理员用户名
    private String username;

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
}
