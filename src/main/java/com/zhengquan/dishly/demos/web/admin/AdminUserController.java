package com.zhengquan.dishly.demos.web.admin;

import com.zhengquan.dishly.entity.ro.AdminUserRequest;
import com.zhengquan.dishly.entity.ro.LoginRequest;
import com.zhengquan.dishly.entity.vo.AccountVo;
import com.zhengquan.dishly.entity.vo.AdminUserVo;
import com.zhengquan.dishly.entity.vo.CurrentUserVo;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.AdminUser;
import com.zhengquan.dishly.service.AdminUserService;
import com.zhengquan.dishly.utils.StpKit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/admin_user")
public class AdminUserController {

    public final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    @PostMapping
    public ResponseEntity<?> addAdminUser(@RequestBody AdminUserRequest adminUserRequest){
        adminUserService.add(adminUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdminUser(@RequestBody AdminUserRequest adminUserRequest){
        adminUserService.updateSelective(adminUserRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "userName",required = false)String userName,
                                  @RequestParam(value = "nickName",required = false)String nickName,
                                  @RequestParam(value = "email",required = false)String email,
                                  @RequestParam(value = "phone",required = false)String phone,
                                  @RequestParam(value = "status",required = false)String status,
                                  @RequestParam("current") Integer current,
                                  @RequestParam("pageSize")Integer pageSize){
        PageResult<AdminUserVo> page = adminUserService.page(userName, nickName, email, phone, status, current, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountVo> login(@RequestBody LoginRequest loginRequest) {

        String password = loginRequest.getPassword();
        String username = loginRequest.getUsername();

        if(adminUserService.login(username,password)){
            return ResponseEntity.ok(
                    new AccountVo().setStatus("ok").setCurrentAuthority("admin")
            );
        }
        return ResponseEntity.ok(
                new AccountVo().setStatus("error").setCurrentAuthority("guest")
        );
    }

    @PostMapping("/outLogin")
    public ResponseEntity<?> outLogin() {
        StpKit.ADMIN.logout();
        return ResponseEntity.ok().build();
    }


    @GetMapping("/admin_user/currentUser")
    public ResponseEntity<CurrentUserVo> currentUser() {

        long userId = StpKit.ADMIN.getLoginIdAsLong();
        AdminUser adminUser = adminUserService.getById(userId);

        CurrentUserVo currentUserVo = new CurrentUserVo();
        CurrentUserVo.Data data = new CurrentUserVo.Data();

        data.setName(adminUser.getNickname())
                .setAvatar(adminUser.getAvatar())
                .setUserid(adminUser.getId().toString())
                .setEmail(adminUser.getEmail())
                .setPhone(adminUser.getPhone());

        currentUserVo.setData(data)
                .setSuccess(true)
                .setErrorCode(null)
                .setErrorMessage(null);

        return ResponseEntity.ok(
                currentUserVo
        );
    }
}
