package com.tcs.sgv.common.valueobject;

// Generated Jun 7, 2007 11:41:43 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * BillVitoRegister generated by hbm2java
 */
public class BillVitoRegister implements java.io.Serializable {

	// Fields    

	private long vitoId;

	private long vitoCode;

	private String vitoType;

	private long billNo;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updtaedDate;

	private String locationCode;

	private long dbId;

	// Constructors

	/** default constructor */
	public BillVitoRegister() {
	}

	/** minimal constructor */
	public BillVitoRegister(long vitoId, long vitoCode, long billNo,
			long createdUserId, long createdPostId, String locationCode, long dbId) {
		this.vitoId = vitoId;
		this.vitoCode = vitoCode;
		this.billNo = billNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.locationCode = locationCode;
		this.dbId = dbId;
	}

	/** full constructor */
	public BillVitoRegister(long vitoId, long vitoCode, String vitoType,
			long billNo, long createdUserId, long createdPostId,
			Date createdDate, Long updatedUserId, Long updatedPostId,
			Date updtaedDate, String locationCode, long dbId) {
		this.vitoId = vitoId;
		this.vitoCode = vitoCode;
		this.vitoType = vitoType;
		this.billNo = billNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updtaedDate = updtaedDate;
		this.locationCode = locationCode;
		this.dbId = dbId;
	}

	// Property accessors
	public long getVitoId() {
		return this.vitoId;
	}

	public void setVitoId(long vitoId) {
		this.vitoId = vitoId;
	}

	public long getVitoCode() {
		return this.vitoCode;
	}

	public void setVitoCode(long vitoCode) {
		this.vitoCode = vitoCode;
	}

	

	public long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(long billNo) {
		this.billNo = billNo;
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

	public Long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdtaedDate() {
		return this.updtaedDate;
	}

	public void setUpdtaedDate(Date updtaedDate) {
		this.updtaedDate = updtaedDate;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public String getVitoType() {
		return vitoType;
	}

	public void setVitoType(String vitoType) {
		this.vitoType = vitoType;
	}

}
