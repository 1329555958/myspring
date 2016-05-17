package com.netfinworks.order.core.repository;

import com.mysema.query.types.Predicate;
import com.netfinworks.order.core.entity.OrderEntity;
import com.netfinworks.order.core.entity.QOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by weichunhe on 2016/5/6.
 */
public class OrderRepositoryImpl implements OrderRepositoryExt {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity findByOrderNo(String orderNo) {
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
        Predicate predicate = qOrderEntity.orderNo.eq(orderNo);
        return orderRepository.findOne(predicate);
    }


}
