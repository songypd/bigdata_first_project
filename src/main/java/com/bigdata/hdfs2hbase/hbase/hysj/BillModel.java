package com.bigdata.hdfs2hbase.hbase.hysj;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yuanpeng.song
 * @create 2018/9/29
 * @since 1.0.0
 */
public class BillModel {
    private String userId;
    private String orderId;
    private String billNumber;
    private Date plannedRepayDate;
    private BigDecimal plannedRepayPrincipal;
    private BigDecimal plannedRepayInterest;
    private BigDecimal plannedRepayAmount;
    private Date repayDate;
    private BigDecimal repayPrincipal;
    /**
     * '实还利息'
     */
    private BigDecimal repayInterest;
    /**
     * '实还费用
     */
    private BigDecimal repayAmount;
    /**
     * 逾期天数
     */
    private int overdueDays;
    private Date createTime;
    private Date updateTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getPlannedRepayDate() {
        return plannedRepayDate;
    }

    public void setPlannedRepayDate(Date plannedRepayDate) {
        this.plannedRepayDate = plannedRepayDate;
    }

    public BigDecimal getPlannedRepayPrincipal() {
        return plannedRepayPrincipal;
    }

    public void setPlannedRepayPrincipal(BigDecimal plannedRepayPrincipal) {
        this.plannedRepayPrincipal = plannedRepayPrincipal;
    }

    public BigDecimal getPlannedRepayInterest() {
        return plannedRepayInterest;
    }

    public void setPlannedRepayInterest(BigDecimal plannedRepayInterest) {
        this.plannedRepayInterest = plannedRepayInterest;
    }

    public BigDecimal getPlannedRepayAmount() {
        return plannedRepayAmount;
    }

    public void setPlannedRepayAmount(BigDecimal plannedRepayAmount) {
        this.plannedRepayAmount = plannedRepayAmount;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getRepayPrincipal() {
        return repayPrincipal;
    }

    public void setRepayPrincipal(BigDecimal repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}