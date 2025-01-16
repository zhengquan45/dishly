package com.zhengquan.dishly.demos.web.api;


import cn.hutool.core.util.StrUtil;
import com.zhengquan.dishly.entity.vo.LocationVo;
import com.zhengquan.dishly.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationApiController {

    private final LocationService locationService;

    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(value = "name",required = false)String name,
                                    @RequestParam(value = "longitude",required = false)BigDecimal longitude,
                                    @RequestParam(value = "latitude",required = false)BigDecimal latitude) {
        if(StrUtil.isNotBlank(name)){
            List<LocationVo> locationVos = locationService.searchTopByName(name);
            return ResponseEntity.ok(locationVos);
        }
        List<LocationVo> locationVos = locationService.searchTopByLocation(longitude,latitude);
        return ResponseEntity.ok(locationVos);
    }

    @GetMapping("/transferGEO")
    public ResponseEntity<?> transferGEO(){
        locationService.transferGEO();
        return ResponseEntity.ok().build();
    }
}
