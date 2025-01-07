package com.zhengquan.dishly.demos.web;

import com.zhengquan.dishly.demos.web.ro.BannerRequest;
import com.zhengquan.dishly.demos.web.ro.ProductRequest;
import com.zhengquan.dishly.demos.web.vo.BannerVo;
import com.zhengquan.dishly.demos.web.vo.PageResult;
import com.zhengquan.dishly.demos.web.vo.ProductVo;
import com.zhengquan.dishly.entity.Banner;
import com.zhengquan.dishly.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banner")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping
    public ResponseEntity<?> addBanner(@RequestBody BannerRequest bannerRequest) {
        bannerService.add(bannerRequest);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<?> editBanner(@RequestBody BannerRequest bannerRequest) {
        bannerService.updateSelective(bannerRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false)String name,
                                  @RequestParam(value = "type",required = false) Banner.BannerType type,
                                  @RequestParam(value="status",required = false)Boolean status,
                                  @RequestParam("current") Integer current,
                                  @RequestParam("pageSize")Integer pageSize) {
        PageResult<BannerVo> page = bannerService.page(name, type, status, current, pageSize);
        return ResponseEntity.ok(page);
    }
}
