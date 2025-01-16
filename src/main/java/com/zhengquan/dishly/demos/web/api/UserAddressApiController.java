package com.zhengquan.dishly.demos.web.api;


import com.zhengquan.dishly.entity.Banner;
import com.zhengquan.dishly.entity.ro.BannerRequest;
import com.zhengquan.dishly.entity.ro.UserAddressRequest;
import com.zhengquan.dishly.entity.vo.BannerVo;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.vo.UserAddressVo;
import com.zhengquan.dishly.service.UserAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAddress")
public class UserAddressApiController {

    private final UserAddressService userAddressService;

    public UserAddressApiController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @GetMapping
    public ResponseEntity<?> page(@RequestParam("current") Integer current,
                                  @RequestParam("pageSize")Integer pageSize) {
        PageResult<UserAddressVo> page = userAddressService.page(current, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<?> addUserAddress(@RequestBody UserAddressRequest userAddressRequest){
        userAddressService.add(userAddressRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editBannerUserAddress(@RequestBody UserAddressRequest userAddressRequest) {
        userAddressService.updateSelective(userAddressRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable Long id){
        userAddressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
