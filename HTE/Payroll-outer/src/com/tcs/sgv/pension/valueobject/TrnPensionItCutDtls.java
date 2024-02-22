package com.tcs.sgv.pension.valueobject;

// default package
// Generated Dec 6, 2007 5:51:40 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionItCutDtls generated by hbm2java
 */
public class TrnPensionItCutDtls implements java.io.Serializable {

	// Fields    

	private Long pensionItCutDtlsId;

	private Long pensionRequestId;

	private String pensionerCode;

	private String typeFlag;

	private Integer fromMonth;

	private Integer toMonth;

	private BigDecimal amount;

	private String remarks;

	private Integer trnCounter;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public TrnPensionItCutDtls() {
	}

	/** minimal constructor */
	public TrnPensionItCutDtls(Long pensionItCutDtlsId) {
		this.pensionItCutDtlsId = pensionItCutDtlsId;
	}

	/** full constructor */
	public TrnPensionItCutDtls(Long pensionItCutDtlsId,
			Long pensionRequestId, String pensionerCode, String typeFlag,
			Integer fromMonth, Integer toMonth, BigDecimal amount,
			String remarks, Integer trnCounter,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate) {
		this.pensionItCutDtlsId = pensionItCutDtlsId;
		this.pensionRequestId = pensionRequestId;
		this.pensionerCode = pensionerCode;
		this.typeFlag = typeFlag;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.amount = amount;
		this.remarks = remarks;
		this.trnCounter = trnCounter;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getPensionItCutDtlsId() {
		return this.pensionItCutDtlsId;
	}

	public void setPensionItCutDtlsId(Long pensionItCutDtlsId) {
		this.pensionItCutDtlsId = pensionItCutDtlsId;
	}

	public Long getPensionRequestId() {
		return this.pensionRequestId;
	}

	public void setPensionRequestId(Long pensionRequestId) {
		this.pensionRequestId = pensionRequestId;
	}

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getTypeFlag() {
		return this.typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public Integer getFromMonth() {
		return this.fromMonth;
	}

	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Integer getToMonth() {
		return this.toMonth;
	}

	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
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
