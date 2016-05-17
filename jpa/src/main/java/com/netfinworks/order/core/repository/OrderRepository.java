package com.netfinworks.order.core.repository;

import com.netfinworks.order.core.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * jpa 接口
 * Created by weichunhe on 2016/5/5.
 */
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, String>, QueryDslPredicateExecutor<OrderEntity>, OrderRepositoryExt {
    /**
     * 根据渠道号和订单编号查询订单
     *
     * @param orderNo
     * @param chanNo
     * @return
     */
    public OrderEntity findFirstByOrderNoAndChanNo(String orderNo, String chanNo);

    @Query("select r from OrderEntity r where r.orderType = ?1")
    public List<OrderEntity> findByOrderType(String orderType);
}
