package com.netfinworks.order.core.repository;

import com.netfinworks.order.core.entity.OrderEntity;
import com.netfinworks.order.core.projection.OrderProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("select r from OrderEntity r where r.orderType = :type")
    public List<OrderEntity> findByOrderType2(@Param("type") String orderType);

    /**
     * 非真正的nativesql
     * @param orderType
     * @return
     */
    @Query(value = "select r.id,r.order_no,r.transact_time,r.amount from mef_order r where r.order_type = :type",nativeQuery = true)
    public List<OrderEntity> findByOrderType3(@Param("type") String orderType);

    /**
     * 暂未研究出来咋用
     * @param orderNo
     * @param orderType
     * @return
     */
    public OrderProjection findByOrderNoAndOrderType(String orderNo, String orderType);


    @Modifying
    @Transactional
    @Query("update OrderEntity o set o.productName = ?1 where o.id = ?2")
    int setProductNameById(String productName, String id);
}
