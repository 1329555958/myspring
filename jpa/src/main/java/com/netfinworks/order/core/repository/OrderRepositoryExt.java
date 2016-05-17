package com.netfinworks.order.core.repository;

import com.netfinworks.order.core.entity.OrderEntity;

/**
 * Created by weichunhe on 2016/5/6.
 */
public interface OrderRepositoryExt {

    public OrderEntity findByOrderNo(String orderNo);

}
