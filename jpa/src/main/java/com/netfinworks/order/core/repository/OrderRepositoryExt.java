package com.netfinworks.order.core.repository;

import com.netfinworks.order.core.entity.OrderEntity;

import java.util.List;

/**
 * Created by weichunhe on 2016/5/6.
 */
public interface OrderRepositoryExt {

    public OrderEntity findByOrderNo(String orderNo);

    public List<OrderEntity> findUseQueryDsl(String  type);

}
