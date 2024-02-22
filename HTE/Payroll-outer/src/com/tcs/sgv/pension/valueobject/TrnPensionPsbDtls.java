package com.tcs.sgv.pension.valueobject;
// Generated Mar 4, 2008 5:52:02 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionPsbDtls generated by hbm2java
 */
public class TrnPensionPsbDtls implements java.io.Serializable {

	// Fields    

	private long psbDtlId;

	private String ppoNo;

	private BigDecimal actualPayment;

	private BigDecimal bankPayment;

	private BigDecimal lessPayment;

	private BigDecimal excessPayment;

	private BigDecimal differenceAmount;

	private String bankCode;

	private BigDecimal voucherNo;

	private int monthYear;

	private BigDecimal dbId;

	private String locationCode;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal incomeTaxCutAmount;

	private BigDecimal pensnCutAmount;

	private BigDecimal specialCutAmount;

	private BigDecimal dpAmount;

	private BigDecimal tiAmount;

	private BigDecimal medicalAllowenceAmount;

	private BigDecimal arrearAmount;

	private BigDecimal recoveryAmount;
	
	private BigDecimal cvpMonthlyAmount;
	
	private BigDecimal basicPension;
	
	private BigDecimal personalPensionAmount;
	
	private BigDecimal irAmount;

	// Constructors

	/** default constructor */
	public TrnPensionPsbDtls() {
	}

	/** minimal constructor */
	public TrnPensionPsbDtls(long psbDtlId, String ppoNo, int monthYear,
			BigDecimal dbId, String locationCode, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate) {
		this.psbDtlId = psbDtlId;
		this.ppoNo = ppoNo;
		this.monthYear = monthYear;
		this.dbId = dbId;
		this.locationCode = locationCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnPensionPsbDtls(long psbDtlId, String ppoNo,
			BigDecimal actualPayment, BigDecimal bankPayment,
			BigDecimal lessPayment, BigDecimal excessPayment,
			BigDecimal differenceAmount, String bankCode, BigDecimal voucherNo,
			int monthYear, BigDecimal dbId, String locationCode,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate,
			BigDecimal incomeTaxCutAmount, BigDecimal pensnCutAmount,
			BigDecimal specialCutAmount, BigDecimal dpAmount,
			BigDecimal tiAmount, BigDecimal medicalAllowenceAmount,
			BigDecimal arrearAmount, BigDecimal recoveryAmount,BigDecimal cvpMonthlyAmount,BigDecimal basicPension,
			BigDecimal personalPensionAmount,BigDecimal irAmount) {
		this.psbDtlId = psbDtlId;
		this.ppoNo = ppoNo;
		this.actualPayment = actualPayment;
		this.bankPayment = bankPayment;
		this.lessPayment = lessPayment;
		this.excessPayment = excessPayment;
		this.differenceAmount = differenceAmount;
		this.bankCode = bankCode;
		this.voucherNo = voucherNo;
		this.monthYear = monthYear;
		this.dbId = dbId;
		this.locationCode = locationCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.incomeTaxCutAmount = incomeTaxCutAmount;
		this.pensnCutAmount = pensnCutAmount;
		this.specialCutAmount = specialCutAmount;
		this.dpAmount = dpAmount;
		this.tiAmount = tiAmount;
		this.medicalAllowenceAmount = medicalAllowenceAmount;
		this.arrearAmount = arrearAmount;
		this.recoveryAmount = recoveryAmount;
		this.cvpMonthlyAmount = cvpMonthlyAmount;
		this.basicPension = basicPension;
		this.personalPensionAmount = personalPensionAmount;
		this.irAmount = irAmount;
	}

	// Property accessors
	public long getPsbDtlId() {
		return this.psbDtlId;
	}

	public void setPsbDtlId(long psbDtlId) {
		this.psbDtlId = psbDtlId;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public BigDecimal getActualPayment() {
		return this.actualPayment;
	}

	public void setActualPayment(BigDecimal actualPayment) {
		this.actualPayment = actualPayment;
	}

	public BigDecimal getBankPayment() {
		return this.bankPayment;
	}

	public void setBankPayment(BigDecimal bankPayment) {
		this.bankPayment = bankPayment;
	}

	public BigDecimal getLessPayment() {
		return this.lessPayment;
	}

	public void setLessPayment(BigDecimal lessPayment) {
		this.lessPayment = lessPayment;
	}

	public BigDecimal getExcessPayment() {
		return this.excessPayment;
	}

	public void setExcessPayment(BigDecimal excessPayment) {
		this.excessPayment = excessPayment;
	}

	public BigDecimal getDifferenceAmount() {
		return this.differenceAmount;
	}

	public void setDifferenceAmount(BigDecimal differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public BigDecimal getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(BigDecimal voucherNo) {
		this.voucherNo = voucherNo;
	}

	public int getMonthYear() {
		return this.monthYear;
	}

	public void setMonthYear(int monthYear) {
		this.monthYear = monthYear;
	}

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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

	public BigDecimal getIncomeTaxCutAmount() {
		return this.incomeTaxCutAmount;
	}

	public void setIncomeTaxCutAmount(BigDecimal incomeTaxCutAmount) {
		this.incomeTaxCutAmount = incomeTaxCutAmount;
	}

	public BigDecimal getPensnCutAmount() {
		return this.pensnCutAmount;
	}

	public void setPensnCutAmount(BigDecimal pensnCutAmount) {
		this.pensnCutAmount = pensnCutAmount;
	}

	public BigDecimal getSpecialCutAmount() {
		return this.specialCutAmount;
	}

	public void setSpecialCutAmount(BigDecimal specialCutAmount) {
		this.specialCutAmount = specialCutAmount;
	}

	public BigDecimal getDpAmount() {
		return this.dpAmount;
	}

	public void setDpAmount(BigDecimal dpAmount) {
		this.dpAmount = dpAmount;
	}

	public BigDecimal getTiAmount() {
		return this.tiAmount;
	}

	public void setTiAmount(BigDecimal tiAmount) {
		this.tiAmount = tiAmount;
	}

	public BigDecimal getMedicalAllowenceAmount() {
		return this.medicalAllowenceAmount;
	}

	public void setMedicalAllowenceAmount(BigDecimal medicalAllowenceAmount) {
		this.medicalAllowenceAmount = medicalAllowenceAmount;
	}

	public BigDecimal getArrearAmount() {
		return this.arrearAmount;
	}

	public void setArrearAmount(BigDecimal arrearAmount) {
		this.arrearAmount = arrearAmount;
	}

	public BigDecimal getRecoveryAmount() {
		return this.recoveryAmount;
	}

	public void setRecoveryAmount(BigDecimal recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}
	
	public BigDecimal getCvpMonthlyAmount() {
		return this.cvpMonthlyAmount;
	}

	public void setCvpMonthlyAmount(BigDecimal cvpMonthlyAmount) {
		this.cvpMonthlyAmount = cvpMonthlyAmount;
	}
	
	public BigDecimal getBasicPension() {
		return this.basicPension;
	}

	public void setBasicPension(BigDecimal basicPension) {
		this.basicPension = basicPension;
	} 
	
	public BigDecimal getPersonalPensionAmount() {
		return this.personalPensionAmount;
	}

	public void setPersonalPensionAmount(BigDecimal personalPensionAmount) {
		this.personalPensionAmount = personalPensionAmount;
	}
	
	public BigDecimal getIrAmount() {
		return this.irAmount;
	}

	public void setIrAmount(BigDecimal irAmount) {
		this.irAmount = irAmount;
	}

}
