package com.zhengquan.dishly.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.entity.Location;
import com.zhengquan.dishly.entity.vo.LocationVo;
import com.zhengquan.dishly.redis.GeoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService extends BaseService<Location> {

    private final GeoService geoService;

    public LocationService(GeoService geoService) {
        this.geoService = geoService;
    }

    public List<LocationVo> searchTopByName(String name) {
        List<Location> locations = getBaseMapper().selectList(Wrappers.lambdaQuery(Location.class)
                .like(Location::getName, name));
        return locations.stream().map(location -> {
            LocationVo locationVo = new LocationVo();
            BeanUtils.copyProperties(location,locationVo);
            return locationVo;
        }).collect(Collectors.toList());
    }

    public void transferGEO() {
        //分页查询全表
        List<Location> locations = getBaseMapper().selectList(null);
        for (Location location : locations) {
            geoService.addLocation(location.getId().toString(),location.getLongitude().doubleValue(),location.getLatitude().doubleValue());
        }
    }

    public List<LocationVo> searchTopByLocation(BigDecimal longitude, BigDecimal latitude) {
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> nearby = geoService.findNearby(longitude.doubleValue(), latitude.doubleValue());
        List<Integer> idList = nearby.stream().map(e -> {
            String id = (String) e.getContent().getName();
            return Integer.valueOf(id);
        }).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(idList)){
            return Collections.emptyList();
        }
        List<Location> locations = getBaseMapper().selectByIds(idList);
        return locations.stream().map(location -> {
            LocationVo locationVo = new LocationVo();
            BeanUtils.copyProperties(location,locationVo);
            return locationVo;
        }).collect(Collectors.toList());
    }
}
