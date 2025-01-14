package com.zhengquan.dishly.service;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengquan.dishly.entity.ro.AdminUserRequest;
import com.zhengquan.dishly.entity.vo.AdminUserVo;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.AdminUser;
import com.zhengquan.dishly.mapper.AdminUserMapper;
import com.zhengquan.dishly.utils.StpKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdminUserService extends BaseService<AdminUser>{

    private final String SALT = "3u12rhbfdiadfnsaodsakop21i32109";

    public final AdminUserMapper adminUserMapper;

    public AdminUserService(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public int add(AdminUserRequest adminUserRequest){
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserRequest,adminUser);
        adminUser.setPassword(SaSecureUtil.sha256BySalt(adminUser.getPassword(),SALT));
        return adminUserMapper.insert(adminUser);
    }

    public void updateSelective(AdminUserRequest adminUserRequest) {
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserRequest,adminUser);
        adminUser.setPassword(SaSecureUtil.sha256BySalt(adminUser.getPassword(),SALT));
        updateSelectiveById(adminUser);
        //TODO 踢用户下线
    }

    public PageResult<AdminUserVo> page(String userName, String nickName, String email, String phone, String status, Integer current, Integer pageSize) {
        Page<AdminUser> page = new Page<AdminUser>(current, pageSize).addOrder(OrderItem.desc(StrUtil.toUnderlineCase(AdminUser.Fields.createdAt)));
        Page<AdminUser> adminUserPage = getBaseMapper().selectPage(page, Wrappers.lambdaQuery(AdminUser.class)
                .like(StrUtil.isNotBlank(userName), AdminUser::getUsername, userName)
                .like(StrUtil.isNotBlank(nickName), AdminUser::getNickname, nickName)
                .like(StrUtil.isNotBlank(email), AdminUser::getEmail, email)
                .like(StrUtil.isNotBlank(phone), AdminUser::getPhone, phone)
                .like(StrUtil.isNotBlank(nickName), AdminUser::getNickname, nickName)
                .eq(Objects.nonNull(status), AdminUser::getStatus, status));
        List<AdminUserVo> adminUserVoList = adminUserPage.getRecords().stream().map(adminUser -> {
            AdminUserVo adminUserVo = new AdminUserVo();
            BeanUtils.copyProperties(adminUser, adminUserVo);
            return adminUserVo;
        }).collect(Collectors.toList());
        return new PageResult<AdminUserVo>()
                .setSuccess(true)
                .setTotal(adminUserPage.getTotal())
                .setData(adminUserVoList)
                .setPageSize(pageSize)
                .setCurrent(current);
    }

    public boolean login(String username, String password) {
        String encryptPassword = SaSecureUtil.sha256BySalt(password, SALT);
        AdminUser adminUser = adminUserMapper.selectOne(Wrappers.lambdaQuery(AdminUser.class)
                .eq(AdminUser::getUsername, username)
                .eq(AdminUser::getPassword, encryptPassword));
        if(Objects.nonNull(adminUser)){
            StpKit.ADMIN.login(adminUser.getId());
            return true;
        }
        return false;
    }
}
