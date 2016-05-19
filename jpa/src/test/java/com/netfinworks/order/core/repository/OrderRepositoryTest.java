package com.netfinworks.order.core.repository;

import com.netfinworks.order.core.App;
import com.netfinworks.order.core.entity.OrderEntity;
import com.netfinworks.order.core.projection.OrderProjection;
import com.netfinworks.util.JSONUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * test
 * Created by weichunhe on 2016/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class OrderRepositoryTest {
    private static Logger log = LoggerFactory.getLogger(OrderRepositoryTest.class);
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test() {
        System.out.print("start test!");
    }

    //    @Test
    public void saveTest() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(UUID.randomUUID().toString());
        orderEntity.setAccountId("111");
        orderEntity.setAmount(new BigDecimal(10));
        orderEntity.setChanNo("1111");
        orderEntity.setCreateAt(new Date());
        orderEntity.setOrderNo(UUID.randomUUID().toString());
        orderEntity.setOrderStatus("execed_success");
        orderEntity.setOrderType("INVEST");
        orderEntity.setProductId("2222");
        orderEntity.setTransactTime(new Date());
        orderEntity.setProductName("12312");
        orderRepository.save(orderEntity);
        List<String> ids = new ArrayList<>();
        ids.add("11111");
        ids.add(orderEntity.getId());
    }
    @Test
    public void testProjection(){
        String orderNo = "9b692ea3-e5dd-4fb0-8cc4-c9c910fc3e08";
        OrderProjection order = orderRepository.findByOrderNoAndOrderType(orderNo,"INVEST");
        log.info("查询:{}-{}",orderNo, JSONUtil.toJson(order));
        Assert.assertTrue(orderNo.equals(order.getOrderId()));
    }
}
