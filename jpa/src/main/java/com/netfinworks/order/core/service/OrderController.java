package com.netfinworks.order.core.service;

import com.netfinworks.order.core.entity.OrderEntity;
import com.netfinworks.order.core.repository.OrderRepository;
import com.netfinworks.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 测试
 * Created by weichunhe on 2016/5/5.
 */
@RestController
@RequestMapping("/test")
public class OrderController {
    private static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository repository;


    @RequestMapping("/orders")
    public Object order() {
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
        orderEntity.setUpdateAt(new Date());
        repository.save(orderEntity);

        log.info("save-{}", JSONUtil.toJson(repository.findFirstByOrderNoAndChanNo(orderEntity.getOrderNo(), orderEntity.getChanNo())));

        orderEntity.setProductName("update");
        repository.save(orderEntity);
        log.info("update-{}", JSONUtil.toJson(repository.findByOrderNo(orderEntity.getOrderNo())));

        log.info("all ={}", JSONUtil.toJson(repository.findAll()));
        List<String> ids = new ArrayList<>();
        ids.add("11111");
        return null;
    }
    @RequestMapping("/findByType")
    public Object find(@RequestParam String type){

        log.info("type:{}",JSONUtil.toJson( repository.findByOrderType(type)));
        log.info("type2:{}",JSONUtil.toJson( repository.findByOrderType2(type)));
        log.info("type3:{}",JSONUtil.toJson( repository.findByOrderType3(type)));
//        log.info("orderNo and type:{}",JSONUtil.toJson(repository.findByOrderNoAndOrderType("9b692ea3-e5dd-4fb0-8cc4-c9c910fc3e08",type)));
        return repository.findByOrderType(type);
    }
}
