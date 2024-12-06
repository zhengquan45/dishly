package com.zhengquan.dishly.demos.web;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhengquan.dishly.demos.web.ro.OrderRequest;
import com.zhengquan.dishly.demos.web.ro.PayOrderRequest;
import com.zhengquan.dishly.entity.Order;
import com.zhengquan.dishly.entity.Product;
import com.zhengquan.dishly.entity.TransactionLog;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.mapper.OrderMapper;
import com.zhengquan.dishly.mapper.ProductMapper;
import com.zhengquan.dishly.mapper.TransactionLogMapper;
import com.zhengquan.dishly.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final ProductMapper productMapper;

    private final UserMapper userMapper;

    private final OrderMapper orderMapper;

    private final TransactionLogMapper transactionLogMapper;

    public OrderController(ProductMapper productMapper, UserMapper userMapper, OrderMapper orderMapper, TransactionLogMapper transactionLogMapper) {
        this.productMapper = productMapper;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
        this.transactionLogMapper = transactionLogMapper;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PayOrderRequest payOrderRequest) {
        Long orderId = payOrderRequest.getOrderId();
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        order.setStatus("PAID");
        orderMapper.updateById(order);
        if (order.getPaymentMethod().equals("CASH")) {
            //TODO 调用微信支付
            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setOrderId(order.getId());
            transactionLog.setAmount(order.getTotalAmount().doubleValue());
            transactionLog.setTransactionType("CASH");
            transactionLog.setRemark("订单");
            transactionLog.setStatus("SUCCESS");
            transactionLog.setUserId(payOrderRequest.getUserId());
            transactionLogMapper.insert(transactionLog);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest) {
        String productId = orderRequest.getProductId();
        Product product = productMapper.selectById(productId);

        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getOpenId, orderRequest.getOpenId()));
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Order order = new Order();
        order.setUserName(user.getContactName());
        order.setUserAddress(user.getDeliveryAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setTotalAmount(product.getPrice());
        order.setUserPhone(user.getContactPhone());
        order.setProductId(product.getId());
        order.setStatus("CREATED");
        orderMapper.insert(order);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> order(@RequestParam String userName,
                                   @RequestParam String paymentMethod) {
        List<Order> orders = orderMapper.selectList(Wrappers.lambdaQuery(Order.class)
                .eq(StrUtil.isNotEmpty(paymentMethod), Order::getPaymentMethod, paymentMethod)
                .eq(StrUtil.isNotEmpty(userName), Order::getUserName, userName));
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<?> order(@PathVariable String orderId) {
        if(orderId!=null){
            Order order = orderMapper.selectById(orderId);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }


}
