package com.zhengquan.dishly.demos.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.demos.web.ro.OrderRequest;
import com.zhengquan.dishly.demos.web.ro.PayOrderRequest;
import com.zhengquan.dishly.demos.web.vo.OrderVo;
import com.zhengquan.dishly.demos.web.vo.PageResult;
import com.zhengquan.dishly.entity.Order;
import com.zhengquan.dishly.entity.Product;
import com.zhengquan.dishly.entity.TransactionLog;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.mapper.OrderMapper;
import com.zhengquan.dishly.mapper.ProductMapper;
import com.zhengquan.dishly.mapper.TransactionLogMapper;
import com.zhengquan.dishly.mapper.UserMapper;
import com.zhengquan.dishly.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/payCallback")
    public ResponseEntity<?> payCallback() {
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest) {
        orderService.add(orderRequest);
        return ResponseEntity.ok().build();
    }
}