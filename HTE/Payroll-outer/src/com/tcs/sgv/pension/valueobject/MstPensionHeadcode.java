package com.tcs.sgv.pension.valueobject;

// Generated May 30, 2008 6:14:06 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPensionHeadcode generated by hbm2java
 */
public class MstPensionHeadcode implements java.io.Serializable {

	// Fields    

	private Long pensionHeadcodeId;

	private BigDecimal headCode;

	private String description;

	private BigDecimal langId;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public MstPensionHeadcode() {
	}

	/** minimal constructor */
	public MstPensionHeadcode(Long pensionHeadcodeId,
			BigDecimal headCode, BigDecimal createdUserId,
			BigDecimal createdPostId) {
		this.pensionHeadcodeId = pensionHeadcodeId;
		this.headCode = headCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public MstPensionHeadcode(Long pensionHeadcodeId,
			BigDecimal headCode, String description, BigDecimal langId,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate) {
		this.pensionHeadcodeId = pensionHeadcodeId;
		this.headCode = headCode;
		this.description = description;
		this.langId = langId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getPensionHeadcodeId() {
		return this.pensionHeadcodeId;
	}

	public void setPensionHeadcodeId(Long pensionHeadcodeId) {
		this.pensionHeadcodeId = pensionHeadcodeId;
	}

	public BigDecimal getHeadCode() {
		return this.headCode;
	}

	public void setHeadCode(BigDecimal headCode) {
		this.headCode = headCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLangId() {
		return this.langId;
	}

	public void setLangId(BigDecimal langId) {
		this.langId = langId;
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
