package com.zhengquan.dishly.demos.web;

import com.zhengquan.dishly.demos.web.ro.ProductRequest;
import com.zhengquan.dishly.demos.web.vo.PageResult;
import com.zhengquan.dishly.demos.web.vo.ProductVo;
import com.zhengquan.dishly.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false)String name,
                                  @RequestParam(value = "category",required = false)String category,
                                  @RequestParam(value="isAvailable",required = false)Boolean isAvailable,
                                  @RequestParam("current") Integer current,
                                  @RequestParam("pageSize")Integer pageSize) {
        PageResult<ProductVo> page = productService.page(name, category, isAvailable, current, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        productService.add(productRequest);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<?> editProduct(@RequestBody ProductRequest productRequest) {
        productService.updateSelective(productRequest);
        return ResponseEntity.ok().build();
    }
}
