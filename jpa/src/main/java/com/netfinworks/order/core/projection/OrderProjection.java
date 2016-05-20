package com.netfinworks.order.core.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by weichunhe on 2016/5/18.
 */

public interface OrderProjection {
    /**
     * http://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.query.spel-expressions
     *
     * @return
     * @Value("#{target.firstName} #{target.lastName}")
     * @Value("#{(target.password == null || target.password.empty) ? null : '******'}")
     */
    @Value("#{target.orderNo}")
    String getOrderId();

    public String getId();
}
