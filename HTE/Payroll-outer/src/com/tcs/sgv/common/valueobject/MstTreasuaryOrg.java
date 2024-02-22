package com.tcs.sgv.common.valueobject;

// Generated May 28, 2007 3:50:36 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * MstTreasuaryOrg generated by hbm2java
 */
public class MstTreasuaryOrg implements java.io.Serializable {

	// Fields    

	private long tsryId;

	private Long distTalukaId;

	private String address1;

	private String address2;

	private String contactPerson;

	private String contactNo;

	private Long langId;

	private short activateFlag;

	private Date startDate;

	private Date endDate;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;
	
	private String locationCode;
	
	private String officeCode;

	// Constructors

	/** default constructor */
	public MstTreasuaryOrg() {
	}

	/** minimal constructor */
	public MstTreasuaryOrg(long tsryId, String address1, short activateFlag, Long langId, 
			long createdUserId, long createdPostId, String locationCode, 
			Date createdDate, String officeCode) {
		this.tsryId = tsryId;
		this.address1 = address1;
		this.langId = langId;
		this.activateFlag = activateFlag;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.locationCode = locationCode;
		this.officeCode = officeCode;
	}

	/** full constructor */
	public MstTreasuaryOrg(long tsryId, Long distTalukaId, String address1,
			String address2, String contactPerson, String contactNo,
			Long langId, short activateFlag, Date startDate, Date endDate,
			long createdUserId, long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId, Date updatedDate, 
			Long dbId, String locationCode, String officeCode) {
		this.tsryId = tsryId;
		this.distTalukaId = distTalukaId;
		this.address1 = address1;
		this.address2 = address2;
		this.contactPerson = contactPerson;
		this.contactNo = contactNo;
		this.langId = langId;
		this.activateFlag = activateFlag;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
		this.officeCode = officeCode;
	}

	// Property accessors
	public long getTsryId() {
		return this.tsryId;
	}

	public void setTsryId(long tsryId) {
		this.tsryId = tsryId;
	}

	public Long getDistTalukaId() {
		return this.distTalukaId;
	}

	public void setDistTalukaId(Long distTalukaId) {
		this.distTalukaId = distTalukaId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Long getLangId() {
		return this.langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public short getActivateFlag() {
		return this.activateFlag;
	}

	public void setActivateFlag(short activateFlag) {
		this.activateFlag = activateFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	public String getLocationCode() {
		return this.locationCode;
	}
	
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	public String getOfficeCode() {
		return this.officeCode;
	}
	
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
