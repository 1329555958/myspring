package com.netfinworks.order.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by weichunhe on 2016/5/5.
 */
@Entity
@Table(name = "mef_order")
public class OrderEntity {
    private String id;
    private String orderNo;
    private Date transactTime;
    private BigDecimal amount;
    private String orderType;
    private String orderStatus;
    private String chanNo;
    private String accountId;
    private String productId;
    private String productName;
    private Date createAt;
    private Date updateAt;
    private boolean deleted;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "transact_time")
    public Date getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "order_type")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "chan_no")
    public String getChanNo() {
        return chanNo;
    }

    public void setChanNo(String chanNo) {
        this.chanNo = chanNo;
    }

    @Basic
    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (deleted != that.deleted) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (transactTime != null ? !transactTime.equals(that.transactTime) : that.transactTime != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (orderType != null ? !orderType.equals(that.orderType) : that.orderType != null) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;
        if (chanNo != null ? !chanNo.equals(that.chanNo) : that.chanNo != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (transactTime != null ? transactTime.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (chanNo != null ? chanNo.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        return result;
    }
}
