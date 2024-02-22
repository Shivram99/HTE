package com.tcs.sgv.common.valueobject;

// Generated Jan 27, 2009 7:20:55 PM by Hibernate Tools 3.2.0.beta8


import java.util.Date;

/**
 * RltDdoOrg generated by hbm2java
 */
public class RltDdoOrg implements java.io.Serializable {

	// Fields    

	private Long ddoOrgId;

	private Long activateFlag;

	private Date startDate;

	private Date endDate;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String ddoCode;

	private String locationCode;

	

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public RltDdoOrg() {
	}

	/** minimal constructor */
	public RltDdoOrg(Long ddoOrgId, Long activateFlag,
			Long createdUserId, Long createdPostId,
			Date createdDate, String ddoCode) {
		this.ddoOrgId = ddoOrgId;
		this.activateFlag = activateFlag;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.ddoCode = ddoCode;
	}

	/** full constructor */
	public RltDdoOrg(Long ddoOrgId, Long activateFlag,
			Date startDate, Date endDate, Long createdUserId,
			Long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId,
			Date updatedDate, Long dbId, String ddoCode,
			String locationCode,  Integer trnCounter) {
		this.ddoOrgId = ddoOrgId;
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
		this.ddoCode = ddoCode;
		this.locationCode = locationCode;
		
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getDdoOrgId() {
		return this.ddoOrgId;
	}

	public void setDdoOrgId(Long ddoOrgId) {
		this.ddoOrgId = ddoOrgId;
	}

	public Long getActivateFlag() {
		return this.activateFlag;
	}

	public void setActivateFlag(Long activateFlag) {
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

	public Long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
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

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

}
