package com.zhengquan.dishly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengquan.dishly.entity.TransactionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {
}
