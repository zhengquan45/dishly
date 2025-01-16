package com.zhengquan.dishly.redis;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GeoService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String GEO_KEY = "locations";

    public GeoService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 添加地点
    public void addLocation(String name, double longitude, double latitude) {
        Point point = new Point(longitude, latitude);
        redisTemplate.opsForGeo().add(GEO_KEY, point, name);
    }

    // 获取地点的地理位置
    public List<Point> getLocation(String... names) {
        return redisTemplate.opsForGeo().position(GEO_KEY, names);
    }

    // 计算两个地点的距离
    public Distance getDistance(String place1, String place2, Metric metric) {
        return redisTemplate.opsForGeo().distance(GEO_KEY, place1, place2, metric);
    }

    // 查询最近 1 公里内的 20 个位置
    public List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> findNearby(double longitude, double latitude, double radiusInKm, int limit) {
        Point center = new Point(longitude, latitude);
        Distance radius = new Distance(radiusInKm, RedisGeoCommands.DistanceUnit.KILOMETERS);
        Circle area = new Circle(center, radius);

        // 查询指定范围和限制数量
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeCoordinates()
                .includeDistance()
                .sortAscending() // 按距离从近到远排序
                .limit(limit);   // 限制返回数量

        // 返回结果
        return redisTemplate.opsForGeo().radius(GEO_KEY, area, args).getContent();
    }

    // 查询最近 1 公里内的 20 个位置
    public List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> findNearby(double longitude, double latitude) {
        return findNearby(longitude,latitude,3,20);
    }
}
