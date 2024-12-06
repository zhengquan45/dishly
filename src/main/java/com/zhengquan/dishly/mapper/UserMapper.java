package com.zhengquan.dishly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengquan.dishly.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void updateByOpenId(User user);
}
