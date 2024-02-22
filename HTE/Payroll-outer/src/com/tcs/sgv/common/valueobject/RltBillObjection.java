package com.tcs.sgv.common.valueobject;

// Generated Jun 6, 2007 9:17:10 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * RltBillObjection generated by hbm2java
 */
public class RltBillObjection implements java.io.Serializable {

	// Fields    

	private long billObjcId;

	private String objectionCode;

	private Long billNo;

	private Long lineItemNo;

	private Long userId;

	private Long postId;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String objFlag;
	
	private String locationCode;

	// Constructors

	/** default constructor */
	public RltBillObjection() {
	}

	/** minimal constructor */
	public RltBillObjection(long billObjcId, long createdUserId,
			long createdPostId, String locationCode, Date createdDate) {
		this.billObjcId = billObjcId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.locationCode = locationCode;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public RltBillObjection(long billObjcId, String objectionCode, Long billNo,
			Long lineItemNo, Long userId, Long postId, long createdUserId,
			long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			String objFlag, String locationCode) {
		this.billObjcId = billObjcId;
		this.objectionCode = objectionCode;
		this.billNo = billNo;
		this.lineItemNo = lineItemNo;
		this.userId = userId;
		this.postId = postId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.objFlag = objFlag;
		this.locationCode = locationCode;
	}

	// Property accessors
	public long getBillObjcId() {
		return this.billObjcId;
	}

	public void setBillObjcId(long billObjcId) {
		this.billObjcId = billObjcId;
	}

	public String getObjectionCode() {
		return this.objectionCode;
	}

	public void setObjectionCode(String objectionId) {
		this.objectionCode = objectionId;
	}

	public Long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public Long getLineItemNo() {
		return this.lineItemNo;
	}

	public void setLineItemNo(Long lineItemNo) {
		this.lineItemNo = lineItemNo;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return this.postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
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

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getObjFlag() {
		return this.objFlag;
	}

	public void setObjFlag(String objFlag) {
		this.objFlag = objFlag;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
}
