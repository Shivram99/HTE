package com.tcs.sgv.exprcpt.valueobject;

// Generated May 22, 2007 12:02:09 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnBsPayEntries generated by hbm2java
 */
public class TrnBsPayEntries implements java.io.Serializable {

	// Fields    

	private long bsPayEntryId;

	private long bsDetailId;

	private Integer paymentNo;

	private String payeeName;

	private Long chequeNo;

	private Date chequeDate;

	private String type;

	private BigDecimal amount;

	private String intl;

	private String remarks;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private long updatedUserId;

	private long updatedPostId;

	private Date updatedDate;

	private long dbId;

	private String locationCode;
	// Constructors

	/** default constructor */
	public TrnBsPayEntries() {
	}

	/** minimal constructor */
	public TrnBsPayEntries(long bsPayEntryId, long bsDetailId,
			String payeeName, String type, long createdUserId,
			long createdPostId, long updatedUserId, long updatedPostId) {
		this.bsPayEntryId = bsPayEntryId;
		this.bsDetailId = bsDetailId;
		this.payeeName = payeeName;
		this.type = type;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
	}

	/** full constructor */
	public TrnBsPayEntries(long bsPayEntryId, long bsDetailId,
			Integer paymentNo, String payeeName, Long chequeNo,
			Date chequeDate, String type, BigDecimal amount, String initial,
			String remarks, long createdUserId, long createdPostId,
			Date createdDate, long updatedUserId, long updatedPostId,
			Date updatedDate, long dbId,String locationCode) {
		this.bsPayEntryId = bsPayEntryId;
		this.bsDetailId = bsDetailId;
		this.paymentNo = paymentNo;
		this.payeeName = payeeName;
		this.chequeNo = chequeNo;
		this.chequeDate = chequeDate;
		this.type = type;
		this.amount = amount;
		this.intl = initial;
		this.remarks = remarks;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	// Property accessors
	public long getBsPayEntryId() {
		return this.bsPayEntryId;
	}

	public void setBsPayEntryId(long bsPayEntryId) {
		this.bsPayEntryId = bsPayEntryId;
	}

	public long getBsDetailId() {
		return this.bsDetailId;
	}

	public void setBsDetailId(long bsDetailId) {
		this.bsDetailId = bsDetailId;
	}

	public Integer getPaymentNo() {
		return this.paymentNo;
	}

	public void setPaymentNo(Integer paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPayeeName() {
		return this.payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Long getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getIntl() {
		return this.intl;
	}

	public void setIntl(String initial) {
		this.intl = intl;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
