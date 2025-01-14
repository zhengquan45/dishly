package com.zhengquan.dishly.demos.web.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhengquan.dishly.entity.ro.UserRequest;
import com.zhengquan.dishly.entity.ro.WechatLoginRequest;
import com.zhengquan.dishly.entity.vo.CurrentUserVo;
import com.zhengquan.dishly.entity.vo.UserVo;
import com.zhengquan.dishly.service.UserService;
import com.zhengquan.dishly.utils.StpKit;
import com.zhengquan.dishly.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SaCheckLogin
@RestController
@RequestMapping("/api/wechat")
public class WeChatLoginController {

    @Value("${wechat.app-id}")
    private String appId;

    @Value("${wechat.app-secret}")
    private String appSecret;

    private final UserService userService;

    public WeChatLoginController(UserService userService) {
        this.userService = userService;
    }

    @SaIgnore
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody WechatLoginRequest wechatLoginRequest) {
        String code = wechatLoginRequest.getCode();
        String encryptedData = wechatLoginRequest.getEncryptedData();
        String iv = wechatLoginRequest.getIv();
        WechatLoginRequest.UserInfo userInfo = wechatLoginRequest.getUserInfo();

        if (!StrUtil.isAllNotEmpty(code,encryptedData,iv)) {
            return ResponseEntity.ok().body("参数错误");
        }

        String url = String.format(
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
            appId, appSecret, code
        );

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // 解析返回的 JSON


        JSONObject json = JSONUtil.parseObj(response);
        if (json.containsKey("errcode")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        String openId = json.getStr("openid");
        String sessionKey = json.getStr("session_key");



        String phoneData = WeChatUtils.decryptPhoneData(encryptedData, iv, sessionKey);
        if (phoneData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("解密失败");
        }

        JSONObject phoneJson = JSONUtil.parseObj(phoneData);
        String phoneNumber = phoneJson.getStr("phoneNumber");


        //获取用户信息并且保存数据库
        UserVo userVo = userService.getUserByOpenId(openId);
        if(!Objects.nonNull(userVo)) {
            UserRequest userRequest = new UserRequest();
            userRequest.setOpenId(openId);
            userRequest.setAvatarUrl(userInfo.getAvatarUrl());
            userRequest.setGender(userInfo.getGender());
            userRequest.setNickname(userInfo.getNickName());
            userRequest.setPhone(phoneNumber);
            userService.add(userRequest);
        }
        StpKit.USER.login(openId);
        // 生成自己的 token
        SaTokenInfo tokenInfo = StpKit.USER.getTokenInfo();

        // 返回 token 给前端
        return ResponseEntity.ok(tokenInfo);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserVo> currentUser() {
        String openId = StpKit.USER.getLoginIdAsString();
        UserVo userVo = userService.getUserByOpenId(openId);
        return ResponseEntity.ok(userVo);
    }
}
