package com.tcs.sgv.stamp.valueobject;
// Generated Oct 15, 2007 12:20:22 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnStampIndentDtls generated by hbm2java
 */
public class TrnStampIndentDtls implements java.io.Serializable {

	// Fields    

	private BigDecimal indDtlId;

	private BigDecimal indNo;

	private short grpCode;

	private int dnmValue;

	private Integer stockOnLastInd;

	private Integer supplyAgainstLastInd;

	private Integer qtySold;

	private Integer qtyDue;

	private Integer qtyRequd;

	private Integer qtyPassed;

	private String remarks;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	private Integer closingBalPrevPeriod;

	// Constructors

	/** default constructor */
	public TrnStampIndentDtls() {
	}

	/** minimal constructor */
	public TrnStampIndentDtls(BigDecimal indDtlId, short grpCode, int dnmValue) {
		this.indDtlId = indDtlId;
		this.grpCode = grpCode;
		this.dnmValue = dnmValue;
	}

	/** full constructor */
	public TrnStampIndentDtls(BigDecimal indDtlId, BigDecimal indNo,
			short grpCode, int dnmValue, Integer stockOnLastInd,
			Integer supplyAgainstLastInd, Integer qtySold, Integer qtyDue,
			Integer qtyRequd, Integer qtyPassed, String remarks,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, BigDecimal dbId,
			Integer closingBalPrevPeriod) {
		this.indDtlId = indDtlId;
		this.indNo = indNo;
		this.grpCode = grpCode;
		this.dnmValue = dnmValue;
		this.stockOnLastInd = stockOnLastInd;
		this.supplyAgainstLastInd = supplyAgainstLastInd;
		this.qtySold = qtySold;
		this.qtyDue = qtyDue;
		this.qtyRequd = qtyRequd;
		this.qtyPassed = qtyPassed;
		this.remarks = remarks;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.closingBalPrevPeriod = closingBalPrevPeriod;
	}

	// Property accessors
	public BigDecimal getIndDtlId() {
		return this.indDtlId;
	}

	public void setIndDtlId(BigDecimal indDtlId) {
		this.indDtlId = indDtlId;
	}

	public BigDecimal getIndNo() {
		return this.indNo;
	}

	public void setIndNo(BigDecimal indNo) {
		this.indNo = indNo;
	}

	public short getGrpCode() {
		return this.grpCode;
	}

	public void setGrpCode(short grpCode) {
		this.grpCode = grpCode;
	}

	public int getDnmValue() {
		return this.dnmValue;
	}

	public void setDnmValue(int dnmValue) {
		this.dnmValue = dnmValue;
	}

	public Integer getStockOnLastInd() {
		return this.stockOnLastInd;
	}

	public void setStockOnLastInd(Integer stockOnLastInd) {
		this.stockOnLastInd = stockOnLastInd;
	}

	public Integer getSupplyAgainstLastInd() {
		return this.supplyAgainstLastInd;
	}

	public void setSupplyAgainstLastInd(Integer supplyAgainstLastInd) {
		this.supplyAgainstLastInd = supplyAgainstLastInd;
	}

	public Integer getQtySold() {
		return this.qtySold;
	}

	public void setQtySold(Integer qtySold) {
		this.qtySold = qtySold;
	}

	public Integer getQtyDue() {
		return this.qtyDue;
	}

	public void setQtyDue(Integer qtyDue) {
		this.qtyDue = qtyDue;
	}

	public Integer getQtyRequd() {
		return this.qtyRequd;
	}

	public void setQtyRequd(Integer qtyRequd) {
		this.qtyRequd = qtyRequd;
	}

	public Integer getQtyPassed() {
		return this.qtyPassed;
	}

	public void setQtyPassed(Integer qtyPassed) {
		this.qtyPassed = qtyPassed;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

	public Integer getClosingBalPrevPeriod() {
		return this.closingBalPrevPeriod;
	}

	public void setClosingBalPrevPeriod(Integer closingBalPrevPeriod) {
		this.closingBalPrevPeriod = closingBalPrevPeriod;
	}

}
