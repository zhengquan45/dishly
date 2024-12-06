package com.zhengquan.dishly.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class BaseService<T> extends ServiceImpl<BaseMapper<T>, T> {

    public boolean updateSelectiveById(T entity) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();

        // 获取实体类的主键ID字段
        Field idField = ReflectionUtils.findField(entity.getClass(), "id");
        if (idField != null) {
            idField.setAccessible(true);
            try {
                Object idValue = idField.get(entity);
                updateWrapper.eq("id", idValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access ID field");
            }
        }

        // 遍历所有字段，动态添加非空字段到更新条件
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value != null && !"id".equals(field.getName())) {
                    updateWrapper.set(StrUtil.toUnderlineCase(field.getName()), value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access field: " + field.getName());
            }
        }
        return update(updateWrapper);
    }
}
