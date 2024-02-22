package com.tcs.sgv.pension.valueobject;

// Generated Dec 4, 2007 12:51:55 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionCaseMvmnt generated by hbm2java
 */
public class TrnPensionCaseMvmnt implements java.io.Serializable {

	// Fields    

	private Long trnPenionCaseMvmntId;

	private String ppoNo;

	private Long pensionRequestId;

	private BigDecimal currentUserId;

	private BigDecimal currentPostId;

	private Short movementId;

	private String movementStatus;

	private String status;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public TrnPensionCaseMvmnt() {
	}

	/** minimal constructor */
	public TrnPensionCaseMvmnt(Long trnPenionCaseMvmntId) {
		this.trnPenionCaseMvmntId = trnPenionCaseMvmntId;
	}

	/** full constructor */
	public TrnPensionCaseMvmnt(Long trnPenionCaseMvmntId, String ppoNo,
			Long pensionRequestId, BigDecimal currentUserId,
			BigDecimal currentPostId, Short movementId, String movementStatus,
			String status, BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate) {
		this.trnPenionCaseMvmntId = trnPenionCaseMvmntId;
		this.ppoNo = ppoNo;
		this.pensionRequestId = pensionRequestId;
		this.currentUserId = currentUserId;
		this.currentPostId = currentPostId;
		this.movementId = movementId;
		this.movementStatus = movementStatus;
		this.status = status;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getTrnPenionCaseMvmntId() {
		return this.trnPenionCaseMvmntId;
	}

	public void setTrnPenionCaseMvmntId(Long trnPenionCaseMvmntId) {
		this.trnPenionCaseMvmntId = trnPenionCaseMvmntId;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public Long getPensionRequestId() {
		return this.pensionRequestId;
	}

	public void setPensionRequestId(Long pensionRequestId) {
		this.pensionRequestId = pensionRequestId;
	}

	public BigDecimal getCurrentUserId() {
		return this.currentUserId;
	}

	public void setCurrentUserId(BigDecimal currentUserId) {
		this.currentUserId = currentUserId;
	}

	public BigDecimal getCurrentPostId() {
		return this.currentPostId;
	}

	public void setCurrentPostId(BigDecimal currentPostId) {
		this.currentPostId = currentPostId;
	}

	public Short getMovementId() {
		return this.movementId;
	}

	public void setMovementId(Short movementId) {
		this.movementId = movementId;
	}

	public String getMovementStatus() {
		return this.movementStatus;
	}

	public void setMovementStatus(String movementStatus) {
		this.movementStatus = movementStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
