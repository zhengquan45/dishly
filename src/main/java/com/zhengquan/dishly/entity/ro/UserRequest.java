package com.zhengquan.dishly.entity.ro;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequest {

    private String openId;

    private String nickname;  // 用户昵称

    private String avatarUrl;  // 用户头像URL

    private String phone;  // 电话

    private Integer gender;  // 用户性别（0: 未知，1: 男，2: 女）


}
