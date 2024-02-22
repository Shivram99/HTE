package com.tcs.sgv.common.valueobject;
// Generated Sep 10, 2007 2:52:27 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;


/**
 * RltBillParty generated by hbm2java
 */
public class RltBillParty implements java.io.Serializable {

	// Fields

	private Long billPartyId;

	private Long billNo;

	private String partyCode;

	private BigDecimal partyAmt = BigDecimal.ZERO;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updtaedDate;

	private Long dbId;

	private String partyName;

	private String partyAddr;

	private String accntNo;

	private String locationCode;

	private Long micrCode;

	private String paymentMode;

	private String bankCode;

	private String branchCode;

	private Date chequeDate;

	private Long chequeNo;

	// Constructors

	/** default constructor */
	public RltBillParty() {

	}

	/** minimal constructor */
	public RltBillParty(Long billPartyId, Long billNo, Long createdUserId, Long createdPostId, String locationCode, Date createdDate) {

		this.billPartyId = billPartyId;
		this.billNo = billNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public RltBillParty(Long billPartyId, Long billNo, String partyCode, BigDecimal partyAmt, Long createdUserId, Long createdPostId, Date createdDate, Long updatedUserId, Long updatedPostId,
			Date updtaedDate, Long dbId, String partyName, String partyAddr, String accntNo, String locationCode, String paymentMode, String bankCode, String branchCode, 
			Date chequeDate, Long chequeNo) {

		this.billPartyId = billPartyId;
		this.billNo = billNo;
		this.partyCode = partyCode;
		this.partyAmt = partyAmt;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updtaedDate = updtaedDate;
		this.dbId = dbId;
		this.partyName = partyName;
		this.partyAddr = partyAddr;
		this.accntNo = accntNo;
		this.locationCode = locationCode;
		this.paymentMode = paymentMode;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.chequeNo = chequeNo;
		this.chequeDate = chequeDate;
	}

	// Property accessors
	public Long getBillPartyId() {

		return this.billPartyId;
	}

	public void setBillPartyId(Long billPartyId) {

		this.billPartyId = billPartyId;
	}

	public Long getBillNo() {

		return this.billNo;
	}

	public void setBillNo(Long billNo) {

		this.billNo = billNo;
	}

	public String getPartyCode() {

		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {

		this.partyCode = partyCode;
	}

	public BigDecimal getPartyAmt() {

		return this.partyAmt;
	}

	public void setPartyAmt(BigDecimal partyAmt) {

		this.partyAmt = partyAmt;
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

	public Date getUpdtaedDate() {

		return this.updtaedDate;
	}

	public void setUpdtaedDate(Date updtaedDate) {

		this.updtaedDate = updtaedDate;
	}

	public Long getDbId() {

		return this.dbId;
	}

	public void setDbId(Long dbId) {

		this.dbId = dbId;
	}

	public String getPartyName() {

		return this.partyName;
	}

	public void setPartyName(String partyName) {

		this.partyName = partyName;
	}

	public String getPartyAddr() {

		return this.partyAddr;
	}

	public void setPartyAddr(String partyAddr) {

		this.partyAddr = partyAddr;
	}

	public String getAccntNo() {

		return this.accntNo;
	}

	public void setAccntNo(String accntNo) {

		this.accntNo = accntNo;
	}

	public String getLocationCode() {

		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {

		this.locationCode = locationCode;
	}

	public Long getMicrCode() {

		return micrCode;
	}

	public void setMicrCode(Long micrCode) {

		this.micrCode = micrCode;
	}

	public String getPaymentMode() {

		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {

		this.paymentMode = paymentMode;
	}

	public String getBankCode() {

		return bankCode;
	}

	public void setBankCode(String bankCode) {

		this.bankCode = bankCode;
	}

	public String getBranchCode() {

		return branchCode;
	}

	public void setBranchCode(String branchCode) {

		this.branchCode = branchCode;
	}

	public Date getChequeDate() {

		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {

		this.chequeDate = chequeDate;
	}

	public Long getChequeNo() {

		return chequeNo;
	}

	public void setChequeNo(Long chequeNo) {

		this.chequeNo = chequeNo;
	}

}
