package com.zhengquan.dishly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengquan.dishly.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
