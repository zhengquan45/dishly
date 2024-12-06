package com.zhengquan.dishly.demos.web;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhengquan.dishly.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//@RestController
//@RequestMapping("/api/wechat")
//public class WeChatLoginController {
//
//    @Value("${wechat.app-id}")
//    private String appId;
//
//    @Value("${wechat.app-secret}")
//    private String appSecret;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
//        String code = requestBody.get("code");
//        if (code == null || code.isEmpty()) {
//            return ResponseEntity.badRequest().body("Code is required");
//        }
//
//        String url = String.format(
//            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
//            appId, appSecret, code
//        );
//
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//
//        // 解析返回的 JSON
//
//
//        JSONObject json = JSONUtil.parseObj(response);
//        if (json.containsKey("errcode")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
//        }
//
//        String openId = json.getStr("openid");
//        String sessionKey = json.getStr("session_key");
//
//        // 生成自己的 token（建议使用 JWT）
//        String token = JwtUtils.generateToken(openId);
//
//        // 返回 token 给前端
//        return ResponseEntity.ok(MapUtil.of("token",token));
//    }
//}
