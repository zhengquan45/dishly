package com.zhengquan.dishly.utils;

import cn.dev33.satoken.stp.StpLogic;

/**
 * StpLogic 门面类，管理项目中所有的 StpLogic 账号体系
 */
public class StpKit {

    /**
     * Admin 会话对象，管理 Admin 表所有账号的登录、权限认证
     */
    public static final StpLogic ADMIN = new StpLogic("admin");

    /**
     * User 会话对象，管理 User 表所有账号的登录、权限认证
     */
    public static final StpLogic USER = new StpLogic("user");

}
