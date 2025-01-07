package com.zhengquan.dishly.demos.web;

import com.zhengquan.dishly.demos.web.ro.LoginRequest;
import com.zhengquan.dishly.demos.web.vo.AccountVo;
import com.zhengquan.dishly.demos.web.vo.CurrentUserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {


    public AccountController() {
    }

    @PostMapping("/login/account")
    public ResponseEntity<AccountVo> login(@RequestBody LoginRequest loginRequest) {

        String password = loginRequest.getPassword();
        String username = loginRequest.getUsername();
        String type = loginRequest.getType();

        if(password.equals("ant.design") && username.equals("admin")){

            return ResponseEntity.ok(
                    new AccountVo().setStatus("ok").setType(type).setCurrentAuthority("admin")
            );
        }
        return ResponseEntity.ok(
                new AccountVo().setStatus("error").setType(type).setCurrentAuthority("guest")
        );
    }


    @GetMapping("/currentUser")
    public ResponseEntity<CurrentUserVo> currentUser() {


        CurrentUserVo currentUserVo = new CurrentUserVo();
        CurrentUserVo.Data data = new CurrentUserVo.Data();

// 设置基本信息
        data.setName("Serati Ma")
                .setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png")
                .setUserid("00000001")
                .setEmail("antdesign@alipay.com")
                .setSignature("海纳百川，有容乃大")
                .setTitle("交互专家")
                .setGroup("蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED")
                .setNotifyCount(12)
                .setUnreadCount(11)
                .setCountry("China")
                .setAccess("getAccess()")
                .setAddress("西湖区工专路 77 号")
                .setPhone("0752-268888888");

// 设置标签
        List<CurrentUserVo.Data.Tag> tags = new ArrayList<>();
        tags.add(new CurrentUserVo.Data.Tag().setKey("0").setLabel("很有想法的"));
        tags.add(new CurrentUserVo.Data.Tag().setKey("1").setLabel("专注设计"));
        tags.add(new CurrentUserVo.Data.Tag().setKey("2").setLabel("辣~"));
        tags.add(new CurrentUserVo.Data.Tag().setKey("3").setLabel("大长腿"));
        tags.add(new CurrentUserVo.Data.Tag().setKey("4").setLabel("川妹子"));
        tags.add(new CurrentUserVo.Data.Tag().setKey("5").setLabel("海纳百川"));
        data.setTags(tags);

// 设置地理信息
        CurrentUserVo.Data.Geographic geographic = new CurrentUserVo.Data.Geographic();
        CurrentUserVo.Data.Geographic.Province province = new CurrentUserVo.Data.Geographic.Province()
                .setLabel("浙江省")
                .setKey("330000");
        CurrentUserVo.Data.Geographic.City city = new CurrentUserVo.Data.Geographic.City()
                .setLabel("杭州市")
                .setKey("330100");
        geographic.setProvince(province).setCity(city);
        data.setGeographic(geographic);

// 将数据填充到 CurrentUserVo
        currentUserVo.setData(data)
                .setSuccess(true)
                .setErrorCode(null)
                .setErrorMessage(null);

        return ResponseEntity.ok(
                currentUserVo
        );
    }
}
