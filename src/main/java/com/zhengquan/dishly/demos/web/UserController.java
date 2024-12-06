package com.zhengquan.dishly.demos.web;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.demos.web.ro.UserRequest;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestParam("open_id") String openId) {
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getOpenId, openId));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> updateUserInfo(@RequestBody UserRequest userRequest) {
        User user = BeanUtil.toBean(userRequest, User.class);
        userMapper.updateByOpenId(user);
        return ResponseEntity.ok().build();
    }





}
