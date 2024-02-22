package com.tcs.sgv.common.valueobject;

// Generated Jan 23, 2009 6:37:47 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * RltDdoBilltype generated by hbm2java
 */
public class RltDdoBilltype implements java.io.Serializable {

	// Fields    

	private Long rltDdobilltypeId;

	private String ddoCode;

	private Long subjectId;

	private Long createdBy;

	private Long createdByPost;

	private Date createdDate;

	private Integer trnCounter;

	private Long dbId;

	// Constructors

	/** default constructor */
	public RltDdoBilltype() {
	}

	/** full constructor */
	public RltDdoBilltype(Long rltDdobilltypeId, String ddoCode,
			Long subjectId, Long createdBy,
			Long createdByPost, Date createdDate, Integer trnCounter,
			Long dbId) {
		this.rltDdobilltypeId = rltDdobilltypeId;
		this.ddoCode = ddoCode;
		this.subjectId = subjectId;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.trnCounter = trnCounter;
		this.dbId = dbId;
	}

	// Property accessors
	public Long getRltDdobilltypeId() {
		return this.rltDdobilltypeId;
	}

	public void setRltDdobilltypeId(Long rltDdobilltypeId) {
		this.rltDdobilltypeId = rltDdobilltypeId;
	}

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(Long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

}
