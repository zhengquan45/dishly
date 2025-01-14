package com.zhengquan.dishly.demos.web.admin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.entity.ro.UserRequest;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "nickname",required = false) String nickname,
                                  @RequestParam(value = "phone",required = false) String phone) {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class)
                .like(StrUtil.isNotBlank(nickname), User::getNickname, nickname)
                .like(StrUtil.isNotBlank(phone), User::getPhone, phone));
        PageResult<User> page = new PageResult<User>()
                .setSuccess(true)
                .setTotal(10L)
                .setData(users)
                .setPageSize(1)
                .setCurrent(1);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{open_id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("open_id") String openId) {
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
