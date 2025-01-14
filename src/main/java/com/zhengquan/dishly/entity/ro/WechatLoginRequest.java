package com.zhengquan.dishly.entity.ro;

import lombok.Data;

@Data
public class WechatLoginRequest {
    private String code;
    private String encryptedData;
    private String iv;
    private UserInfo userInfo;

    @Data
    public static class UserInfo {
        private String nickName;
        private int gender;
        private String language;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
    }
}
