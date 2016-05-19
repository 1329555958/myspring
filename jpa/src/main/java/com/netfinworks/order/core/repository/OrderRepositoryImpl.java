package com.netfinworks.order.core.repository;

import com.mysema.query.jpa.JPQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.netfinworks.order.core.entity.OrderEntity;
import com.netfinworks.order.core.entity.QOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by weichunhe on 2016/5/6.
 */
public class OrderRepositoryImpl implements OrderRepositoryExt {

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JPQLTemplates jpqlTemplates;

    @Override
    public OrderEntity findByOrderNo(String orderNo) {
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
        Predicate predicate = qOrderEntity.orderNo.eq(orderNo);
        return orderRepository.findOne(predicate);
    }


    public List<OrderEntity> findById(String  id){
        QOrderEntity qOrder = QOrderEntity.orderEntity;
        JPAQuery query = new JPAQuery(entityManager,jpqlTemplates);
        return query.from(qOrder).where(qOrder.id.eq(id)).list(qOrder);
    }

}
