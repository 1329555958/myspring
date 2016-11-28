package com.netfinworks.recon.domainservice.recon.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>清算流水</p>
 *
 * @author gxx
 * @version $Id: ChannelGlide.java, v 0.1 2016年8月19日 下午1:43:33 guanxiaoxu Exp $
 */
public class BankGlide implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9095581107734541334L;
    private String fundsChannel;
    private String bizNo;
    private String bankDate;
    private String bizType;
    private BigDecimal amount;
    private String status;
    private String batchNo;

    //-----

    public BankGlide(String fundsChannel, String bizNo, String bankDate, String bizType,
                     BigDecimal amount, String status, String batchNo) {
        this.fundsChannel = fundsChannel;
        this.bizNo = bizNo;
        this.bankDate = bankDate;
        this.bizType = bizType;
        this.amount = amount;
        this.status = status;
        this.batchNo = batchNo;
    }

    public BankGlide() {
    }

    public String getFundsChannel() {
        return fundsChannel;
    }

    public void setFundsChannel(String fundsChannel) {
        this.fundsChannel = fundsChannel;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

}
