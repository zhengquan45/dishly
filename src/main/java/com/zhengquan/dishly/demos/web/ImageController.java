package com.zhengquan.dishly.demos.web;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yazhuo.SMMSClient;
import com.github.yazhuo.reponse.ImageItem;
import com.zhengquan.dishly.demos.web.ro.OrderRequest;
import com.zhengquan.dishly.demos.web.vo.ImageVo;
import com.zhengquan.dishly.entity.Order;
import com.zhengquan.dishly.entity.Product;
import com.zhengquan.dishly.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    // 上传文件的 API
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("上传的文件为空！");
        }

        // 指定保存文件的路径
        String uploadDir = "/Users/zhengquan/uploads";  // 替换为你的保存路径
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();  // 如果目录不存在，则创建
        }

        try {
            // 保存文件到指定目录
            File destFile = new File(uploadDir + "/" + file.getOriginalFilename());
            file.transferTo(destFile);
            SMMSClient smmsClient = new SMMSClient("YD2OPwjsf7mtY6fpBIn4D2UoCYgEPUCv");
            ImageItem imageItem = smmsClient.upload(destFile);
            String url = imageItem.getUrl();
            return ResponseEntity.ok(new ImageVo().setUrl(url));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("文件上传失败: " + e.getMessage());
        }
    }


}
