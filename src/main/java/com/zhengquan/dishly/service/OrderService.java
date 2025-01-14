package com.zhengquan.dishly.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhengquan.dishly.entity.ro.OrderDetailRequest;
import com.zhengquan.dishly.entity.ro.OrderRequest;
import com.zhengquan.dishly.entity.vo.OrderVo;
import com.zhengquan.dishly.entity.vo.PageResult;
import com.zhengquan.dishly.entity.Order;
import com.zhengquan.dishly.entity.OrderDetail;
import com.zhengquan.dishly.entity.Product;
import com.zhengquan.dishly.entity.User;
import com.zhengquan.dishly.mapper.OrderDetailMapper;
import com.zhengquan.dishly.mapper.ProductMapper;
import com.zhengquan.dishly.mapper.TransactionLogMapper;
import com.zhengquan.dishly.mapper.UserMapper;
import com.zhengquan.dishly.utils.OrderNoGeneratorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService extends BaseService<Order> {

    private final OrderDetailMapper orderDetailMapper;
    private final ProductMapper productMapper;
    private final UserMapper userMapper;
    private final TransactionLogMapper transactionLogMapper;

    public OrderService(OrderDetailMapper orderDetailMapper, ProductMapper productMapper, UserMapper userMapper, TransactionLogMapper transactionLogMapper) {
        this.orderDetailMapper = orderDetailMapper;
        this.productMapper = productMapper;
        this.userMapper = userMapper;
        this.transactionLogMapper = transactionLogMapper;
    }

    public OrderVo get(Long orderId) {
        if (orderId != null) {
            Order order = getBaseMapper().selectById(orderId);
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            return orderVo;
        }
        return null;
    }

    private Map<Long, Product> getProductMap(List<Long> productIdList) {
        List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class)
                .in(Product::getId, productIdList));
        return products.stream().collect(Collectors.toMap(Product::getId, product -> product));
    }


    @Transactional(rollbackFor = Exception.class)
    public int add(OrderRequest orderRequest) {
        List<OrderDetailRequest> orderDetailRequestList = orderRequest.getOrderDetailRequestList();

        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getOpenId, orderRequest.getOpenId()));
        if (user == null) {
            return 0;
        }
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        List<Long> productIdList = orderDetailRequestList.stream().map(OrderDetailRequest::getProductId).collect(Collectors.toList());
        Map<Long, Product> productMap = getProductMap(productIdList);

        for (OrderDetailRequest orderDetailRequest : orderDetailRequestList) {
            Product product = productMap.get(orderDetailRequest.getProductId());
            BigDecimal productPrice = NumberUtil.mul(product.getPrice(), orderDetailRequest.getProductNum());
            totalAmount = NumberUtil.add(totalAmount, productPrice);


        }
        Order order = new Order();
        order.setOrderNo(OrderNoGeneratorUtils.generateOrderId());
        order.setUserId(user.getId());
//        order.setDeliveryAddress(user.getDeliveryAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setTotalAmount(totalAmount);
        order.setStatus("CREATED");
        int orderNum = getBaseMapper().insert(order);
        List<OrderDetail> orderDetailList = CollectionUtil.newArrayList();
        orderDetailRequestList.forEach(orderDetailRequest -> {
            OrderDetail orderDetail = new OrderDetail()
                    .setOrderId(order.getId())
                    .setProductId(orderDetailRequest.getProductId())
                    .setProductNum(orderDetailRequest.getProductNum());
            orderDetailList.add(orderDetail);
        });
        orderDetailMapper.insert(orderDetailList);
        return orderNum;
    }

    public PageResult<OrderVo> page(String orderNo,
                                    String deliveryAddress,
                                    String paymentMethod,
                                    String status,
                                    String name,
                                    Integer current,
                                    Integer pageSize) {
        List<User> userList = userMapper.selectList(Wrappers.lambdaQuery(User.class)
                .select(User::getId)
                .or()
                .like(User::getNickname, name));
        List<Long> userIdList = userList.stream().map(User::getId).collect(Collectors.toList());

        Page<Order> orderPage = getBaseMapper().selectPage(Page.of(current, pageSize), Wrappers.lambdaQuery(Order.class)
                .like(StrUtil.isNotBlank(orderNo), Order::getOrderNo, orderNo)
                .like(StrUtil.isNotBlank(deliveryAddress), Order::getDeliveryAddress, deliveryAddress)
                .eq(Objects.nonNull(paymentMethod), Order::getPaymentMethod, paymentMethod)
                .eq(Objects.nonNull(status), Order::getStatus, status)
                .in(CollectionUtil.isNotEmpty(userIdList), Order::getUserId, userIdList));
        List<OrderVo> orderVoList = orderPage.getRecords().stream().map(order -> {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            return orderVo;
        }).collect(Collectors.toList());
        return new PageResult<OrderVo>()
                .setSuccess(true)
                .setTotal(orderPage.getTotal())
                .setData(orderVoList)
                .setPageSize(pageSize)
                .setCurrent(current);
    }
}
