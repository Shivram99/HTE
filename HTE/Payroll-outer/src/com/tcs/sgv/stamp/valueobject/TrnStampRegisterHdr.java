package com.tcs.sgv.stamp.valueobject;
// Generated Oct 19, 2007 1:10:17 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnStampRegisterHdr generated by hbm2java
 */
public class TrnStampRegisterHdr implements java.io.Serializable {

	// Fields    

	private BigDecimal trnRegId;

	private String typeCode;

	private BigDecimal refDocNo;

	private long locCode;

	private Date trnDate;

	private Short vendorCode;

	private String vendorPartyName;

	private String partyAddress;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	private BigDecimal grossAmt;

	// Constructors

	/** default constructor */
	public TrnStampRegisterHdr() {
	}

	/** minimal constructor */
	public TrnStampRegisterHdr(BigDecimal trnRegId, String typeCode,
			BigDecimal refDocNo, long locCode, Date trnDate) {
		this.trnRegId = trnRegId;
		this.typeCode = typeCode;
		this.refDocNo = refDocNo;
		this.locCode = locCode;
		this.trnDate = trnDate;
	}

	/** full constructor */
	public TrnStampRegisterHdr(BigDecimal trnRegId, String typeCode,
			BigDecimal refDocNo, long locCode, Date trnDate, Short vendorCode,
			String vendorPartyName, String partyAddress,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, BigDecimal dbId,
			BigDecimal grossAmt) {
		this.trnRegId = trnRegId;
		this.typeCode = typeCode;
		this.refDocNo = refDocNo;
		this.locCode = locCode;
		this.trnDate = trnDate;
		this.vendorCode = vendorCode;
		this.vendorPartyName = vendorPartyName;
		this.partyAddress = partyAddress;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.grossAmt = grossAmt;
	}

	// Property accessors
	public BigDecimal getTrnRegId() {
		return this.trnRegId;
	}

	public void setTrnRegId(BigDecimal trnRegId) {
		this.trnRegId = trnRegId;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public BigDecimal getRefDocNo() {
		return this.refDocNo;
	}

	public void setRefDocNo(BigDecimal refDocNo) {
		this.refDocNo = refDocNo;
	}

	public long getLocCode() {
		return this.locCode;
	}

	public void setLocCode(long locCode) {
		this.locCode = locCode;
	}

	public Date getTrnDate() {
		return this.trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public Short getVendorCode() {
		return this.vendorCode;
	}

	public void setVendorCode(Short vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorPartyName() {
		return this.vendorPartyName;
	}

	public void setVendorPartyName(String vendorPartyName) {
		this.vendorPartyName = vendorPartyName;
	}

	public String getPartyAddress() {
		return this.partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
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

	public BigDecimal getGrossAmt() {
		return this.grossAmt;
	}

	public void setGrossAmt(BigDecimal grossAmt) {
		this.grossAmt = grossAmt;
	}

}
