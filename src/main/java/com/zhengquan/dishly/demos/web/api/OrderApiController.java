package com.zhengquan.dishly.demos.web.api;

import com.zhengquan.dishly.entity.ro.OrderRequest;
import com.zhengquan.dishly.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    private final OrderService orderService;

    public OrderApiController(OrderService orderService) {
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