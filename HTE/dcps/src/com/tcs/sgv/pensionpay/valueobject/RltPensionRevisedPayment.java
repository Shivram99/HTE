package com.tcs.sgv.pensionpay.valueobject;

// default package
// Generated Jun 25, 2008 12:39:39 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * RltPensionRevisedPayment generated by hbm2java
 */
public class RltPensionRevisedPayment implements java.io.Serializable {

	// Fields    

	private Long pensionRevisedPaymentId;

	private Long headcodeRatePk;

	private String fieldType;

	private Integer forPayMonth;

	private Integer toPayMonth;

	private Integer payInMonth;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public RltPensionRevisedPayment() {
	}

	/** minimal constructor */
	public RltPensionRevisedPayment(Long pensionRevisedPaymentId,
			BigDecimal createdUserId, BigDecimal createdPostId) {
		this.pensionRevisedPaymentId = pensionRevisedPaymentId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public RltPensionRevisedPayment(Long pensionRevisedPaymentId,
			Long headcodeRatePk, String fieldType, Integer forPayMonth,
			Integer toPayMonth, Integer payInMonth, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId, Date updatedDate) {
		this.pensionRevisedPaymentId = pensionRevisedPaymentId;
		this.headcodeRatePk = headcodeRatePk;
		this.fieldType = fieldType;
		this.forPayMonth = forPayMonth;
		this.toPayMonth = toPayMonth;
		this.payInMonth = payInMonth;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getPensionRevisedPaymentId() {
		return this.pensionRevisedPaymentId;
	}

	public void setPensionRevisedPaymentId(Long pensionRevisedPaymentId) {
		this.pensionRevisedPaymentId = pensionRevisedPaymentId;
	}

	public Long getHeadcodeRatePk() {
		return this.headcodeRatePk;
	}

	public void setHeadcodeRatePk(Long headcodeRatePk) {
		this.headcodeRatePk = headcodeRatePk;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getForPayMonth() {
		return this.forPayMonth;
	}

	public void setForPayMonth(Integer forPayMonth) {
		this.forPayMonth = forPayMonth;
	}

	public Integer getToPayMonth() {
		return this.toPayMonth;
	}

	public void setToPayMonth(Integer toPayMonth) {
		this.toPayMonth = toPayMonth;
	}

	public Integer getPayInMonth() {
		return this.payInMonth;
	}

	public void setPayInMonth(Integer payInMonth) {
		this.payInMonth = payInMonth;
	}

	public BigDecimal getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(BigDecimal createdUserId) {
		this.createdUserId = createdUserId;
	}

	public BigDecimal getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(BigDecimal createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(BigDecimal updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public BigDecimal getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(BigDecimal updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
