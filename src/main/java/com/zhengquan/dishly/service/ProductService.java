package com.zhengquan.dishly.service;

import com.zhengquan.dishly.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product> {

    public boolean updateUserSelective(Product product) {
        return updateSelectiveById(product);
    }
}
