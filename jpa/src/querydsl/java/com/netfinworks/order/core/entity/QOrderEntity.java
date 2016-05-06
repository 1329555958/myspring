package com.netfinworks.order.core.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = -2095389298L;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final StringPath accountId = createString("accountId");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath chanNo = createString("chanNo");

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath id = createString("id");

    public final StringPath orderNo = createString("orderNo");

    public final StringPath orderStatus = createString("orderStatus");

    public final StringPath orderType = createString("orderType");

    public final StringPath productId = createString("productId");

    public final StringPath productName = createString("productName");

    public final DateTimePath<java.util.Date> transactTime = createDateTime("transactTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> updateAt = createDateTime("updateAt", java.util.Date.class);

    public QOrderEntity(String variable) {
        super(OrderEntity.class, forVariable(variable));
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderEntity(PathMetadata<?> metadata) {
        super(OrderEntity.class, metadata);
    }

}

