package com.zhengquan.dishly.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNoGeneratorUtils {

    // 定义福州地区区号
    private static final String CITY_CODE = "FZ";

    // 每日订单流水号计数器
    private static AtomicInteger dailyOrderCount = new AtomicInteger(0);

    // 保存当前日期，用于重置流水号
    private static String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * 生成订单号
     *
     * @return 订单号字符串
     */
    public static synchronized String generateOrderId() {
        // 获取当前日期
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 如果日期变更，重置计数器
        if (!today.equals(currentDate)) {
            currentDate = today;
            dailyOrderCount.set(0);
        }

        // 获取流水号（补齐5位）
        String serialNumber = String.format("%05d", dailyOrderCount.incrementAndGet());

        // 拼接订单号
        return CITY_CODE + today + serialNumber;
    }

    public static void main(String[] args) {
        // 测试生成多个订单号
        for (int i = 0; i < 10; i++) {
            System.out.println(generateOrderId());
        }
    }
}
