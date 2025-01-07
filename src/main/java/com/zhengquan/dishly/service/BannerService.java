package com.zhengquan.dishly.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengquan.dishly.demos.web.ro.BannerRequest;
import com.zhengquan.dishly.demos.web.vo.BannerVo;
import com.zhengquan.dishly.demos.web.vo.PageResult;
import com.zhengquan.dishly.demos.web.vo.ProductVo;
import com.zhengquan.dishly.entity.Banner;
import com.zhengquan.dishly.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BannerService extends BaseService<Banner> {

    public int add(BannerRequest bannerRequest) {
        Banner banner = BeanUtil.toBean(bannerRequest, Banner.class);
        banner.setStartTime(bannerRequest.getDateRange()[0]);
        banner.setEndTime(bannerRequest.getDateRange()[1]);
        return getBaseMapper().insert(banner);
    }

    public void updateSelective(BannerRequest bannerRequest) {
        Banner banner = BeanUtil.toBean(bannerRequest, Banner.class);
        banner.setStartTime(bannerRequest.getDateRange()[0]);
        banner.setEndTime(bannerRequest.getDateRange()[1]);
        updateSelectiveById(banner);
    }

    public PageResult<BannerVo> page(String name, Banner.BannerType type, Boolean status, Integer current, Integer pageSize) {
        Page<Banner> bannerPage = getBaseMapper().selectPage(Page.of(current, pageSize), Wrappers.lambdaQuery(Banner.class)
                .like(StrUtil.isNotBlank(name), Banner::getName, name)
                .eq(Objects.nonNull(type), Banner::getType, type)
                .eq(Objects.nonNull(status), Banner::getStatus, status));
        List<BannerVo> bannerVoList = bannerPage.getRecords().stream().map(banner -> {
            BannerVo bannerVo = new BannerVo();
            BeanUtils.copyProperties(banner, bannerVo);
            bannerVo.setDateRange(new LocalDateTime[]{
                    banner.getStartTime(),
                    banner.getEndTime()
            });
            return bannerVo;
        }).collect(Collectors.toList());
        return new PageResult<BannerVo>()
                .setSuccess(true)
                .setTotal(bannerVoList.size())
                .setData(bannerVoList)
                .setPageSize(pageSize)
                .setCurrent(current);
    }
}
