package com.zhengquan.dishly.demos.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.demos.web.ro.ProductRequest;
import com.zhengquan.dishly.demos.web.vo.PageResult;
import com.zhengquan.dishly.entity.Product;
import com.zhengquan.dishly.mapper.ProductMapper;
import com.zhengquan.dishly.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "category",required = false)String category,
                                  @RequestParam(value="isAvailable",required = false)Boolean isAvailable) {
        List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class)
                        .like(StrUtil.isNotBlank(name),Product::getName, name)
                        .eq(StrUtil.isNotEmpty(category), Product::getCategory, category)
                        .eq(Objects.nonNull(isAvailable),Product::getIsAvailable,isAvailable));
        PageResult<Product> page = new PageResult<Product>()
                .setSuccess(true)
                .setTotal(products.size())
                .setData(products)
                .setPageSize(1)
                .setCurrent(1);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = BeanUtil.toBean(productRequest, Product.class);
        productMapper.insert(product);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<?> editProduct(@RequestBody ProductRequest productRequest) {
        Product product = BeanUtil.toBean(productRequest, Product.class);
        productService.updateUserSelective(product);
        return ResponseEntity.ok().build();
    }
}
