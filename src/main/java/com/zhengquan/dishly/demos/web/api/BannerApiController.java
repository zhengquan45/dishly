package com.zhengquan.dishly.demos.web.api;

import com.zhengquan.dishly.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/banner")
public class BannerApiController {

    private final BannerService bannerService;

    public BannerApiController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/main")
    public ResponseEntity<?> main() {
        return ResponseEntity.ok(bannerService.getBanner());
    }
}
