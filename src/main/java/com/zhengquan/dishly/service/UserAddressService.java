package com.zhengquan.dishly.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengquan.dishly.entity.Banner;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.entity.UserAddress;
import com.zhengquan.dishly.entity.ro.UserAddressRequest;
import com.zhengquan.dishly.entity.vo.BannerVo;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.vo.UserAddressVo;
import com.zhengquan.dishly.mapper.UserMapper;
import com.zhengquan.dishly.utils.StpKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserAddressService extends BaseService<UserAddress> {

    private final UserMapper userMapper;

    public UserAddressService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int add(UserAddressRequest userAddressRequest) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressRequest, userAddress);
        String openId = StpKit.USER.getLoginIdAsString();
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getOpenId, openId));
        if (Objects.nonNull(user)) {
            userAddress.setUserId(user.getId());
        }
        return getBaseMapper().insert(userAddress);
    }


    public void updateSelective(UserAddressRequest userAddressRequest) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressRequest, userAddress);
        updateSelectiveById(userAddress);
    }

    public PageResult<UserAddressVo> page(Integer current, Integer pageSize) {
        Page<UserAddress> userAddressPage = getBaseMapper().selectPage(new Page<>(current, pageSize), null);
        List<UserAddressVo> userAddressVoList = userAddressPage.getRecords().stream().map(userAddress -> {
            UserAddressVo userAddressVo = new UserAddressVo();
            BeanUtils.copyProperties(userAddress, userAddressVo);
            return userAddressVo;
        }).collect(Collectors.toList());
        return new PageResult<UserAddressVo>()
                .setSuccess(true)
                .setTotal(userAddressPage.getTotal())
                .setData(userAddressVoList)
                .setPageSize(pageSize)
                .setCurrent(current);
    }

    public int delete(Long id) {
        UserAddress userAddress = getBaseMapper().selectById(id);
        if(Objects.isNull(userAddress)){
            return 0;
        }
        Long userId = userAddress.getUserId();
        User user = userMapper.selectById(userId);
        if(StrUtil.equals(user.getOpenId(),StpKit.USER.getLoginIdAsString())){
            return 0;
        }
        return getBaseMapper().deleteById(id);
    }
}
