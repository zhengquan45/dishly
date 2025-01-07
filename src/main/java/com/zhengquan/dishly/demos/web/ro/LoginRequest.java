package com.zhengquan.dishly.demos.web.ro;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private boolean autoLogin;
    private String type;
}