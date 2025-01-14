package com.zhengquan.dishly.demos.web.api;

import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.vo.ProductVo;
import com.zhengquan.dishly.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "category",required = false)String category,
                                  @RequestParam("current") Integer current,
                                  @RequestParam("pageSize")Integer pageSize) {
        PageResult<ProductVo> page = productService.page(null,category,true,current,pageSize);
        return ResponseEntity.ok(page);
    }
}
