package com.zhengquan.dishly.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.entity.ro.UserRequest;
import com.zhengquan.dishly.entity.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService extends BaseService<User>{

    public int add(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        return getBaseMapper().insert(user);
    }

    public UserVo getUserByOpenId(String openId) {
        User user = getBaseMapper().selectOne(Wrappers.lambdaQuery(User.class).eq(User::getOpenId, openId));
        if(Objects.isNull(user)){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}
