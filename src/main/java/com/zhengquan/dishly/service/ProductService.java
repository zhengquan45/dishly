package com.zhengquan.dishly.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengquan.dishly.entity.ro.ProductRequest;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.vo.ProductVo;
import com.zhengquan.dishly.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseService<Product> {

    public void updateSelective(ProductRequest productRequest) {
        Product product = BeanUtil.toBean(productRequest, Product.class);
        updateSelectiveById(product);
    }

    public PageResult<ProductVo> page(String name, String category, Boolean isAvailable, Integer current, Integer pageSize) {
        Page<Product> page = new Page<Product>(current, pageSize).addOrder(OrderItem.desc(StrUtil.toUnderlineCase(Product.Fields.createdAt)));
        Page<Product> productPage = getBaseMapper().selectPage(page, Wrappers.lambdaQuery(Product.class)
                .like(StrUtil.isNotBlank(name), Product::getName, name)
                .eq(StrUtil.isNotEmpty(category), Product::getCategory, category)
                .eq(Objects.nonNull(isAvailable), Product::getIsAvailable, isAvailable));
        List<ProductVo> productVoList = productPage.getRecords().stream().map(product -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            return productVo;
        }).collect(Collectors.toList());
        return new PageResult<ProductVo>()
                .setSuccess(true)
                .setTotal(productPage.getTotal())
                .setData(productVoList)
                .setPageSize(pageSize)
                .setCurrent(current);
    }

    public int add(ProductRequest productRequest) {
        Product product = BeanUtil.toBean(productRequest, Product.class);
        return getBaseMapper().insert(product);
    }
}
