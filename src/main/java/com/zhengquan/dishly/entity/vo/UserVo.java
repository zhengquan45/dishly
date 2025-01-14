package com.zhengquan.dishly.entity.vo;

import lombok.Data;

@Data
public class UserVo {

    private String openId;  // 微信OpenId（唯一标识）

    private String nickname;  // 用户昵称

    private String avatarUrl;  // 用户头像URL

    private String phone;  // 电话

    private Integer gender;  // 用户性别（0: 未知，1: 男，2: 女）
}
