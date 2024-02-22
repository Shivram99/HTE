package com.tcs.sgv.pension.valueobject;
// Generated Apr 15, 2008 12:23:31 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;

/**
 * TrnPensionBillDtls generated by hbm2java
 */
public class TrnPensionBillDtls implements java.io.Serializable {

	// Fields    

	private Long trnPensionBillDtlsId;

	private String ppoNo;

	private String pensionerCode;

	private BigDecimal incomeTaxCutAmount;

	private BigDecimal pensnCutAmount;

	private BigDecimal specialCutAmount;

	private BigDecimal cvpMonthAmount;

	private BigDecimal pensionAmount;

	private BigDecimal dpAmount;

	private BigDecimal tiAmount;

	private BigDecimal medicalAllowenceAmount;

	private BigDecimal arrearAmount;

	private BigDecimal recoveryAmount;

	private BigDecimal reducedPension;

	private String accountNo;

	private Long trnPensionBillHdrId;

	private String pensionerName;

	private BigDecimal allcationBf11156;

	private BigDecimal allcationAf11156;

	private BigDecimal allcationAf10560;

	private BigDecimal personalPensionAmount;

	private BigDecimal irAmount;

	private BigDecimal moCommission;

	private BigDecimal tiArrearAmount;

	private BigDecimal omr;

	private BigDecimal otherBenefits;

	private BigDecimal lta;

    private Integer trnCounter;
	// Constructors

	/** default constructor */
	public TrnPensionBillDtls() {
	}

	/** minimal constructor */
	public TrnPensionBillDtls(Long trnPensionBillDtlsId, String ppoNo,
			String pensionerCode) {
		this.trnPensionBillDtlsId = trnPensionBillDtlsId;
		this.ppoNo = ppoNo;
		this.pensionerCode = pensionerCode;
	}

	/** full constructor */
	public TrnPensionBillDtls(Long trnPensionBillDtlsId, String ppoNo,
			String pensionerCode, BigDecimal incomeTaxCutAmount,
			BigDecimal pensnCutAmount, BigDecimal specialCutAmount,
			BigDecimal cvpMonthAmount, BigDecimal pensionAmount,
			BigDecimal dpAmount, BigDecimal tiAmount,
			BigDecimal medicalAllowenceAmount, BigDecimal arrearAmount,
			BigDecimal recoveryAmount, BigDecimal reducedPension,
			String accountNo, Long trnPensionBillHdrId,
			String pensionerName, BigDecimal allcationBf11156,
			BigDecimal allcationAf11156, BigDecimal allcationAf10560,
			BigDecimal personalPensionAmount, BigDecimal irAmount,
			BigDecimal moCommission, BigDecimal tiArrearAmount, BigDecimal omr,
			BigDecimal otherBenefits, BigDecimal lta,Integer trnCounter) {
		this.trnPensionBillDtlsId = trnPensionBillDtlsId;
		this.ppoNo = ppoNo;
		this.pensionerCode = pensionerCode;
		this.incomeTaxCutAmount = incomeTaxCutAmount;
		this.pensnCutAmount = pensnCutAmount;
		this.specialCutAmount = specialCutAmount;
		this.cvpMonthAmount = cvpMonthAmount;
		this.pensionAmount = pensionAmount;
		this.dpAmount = dpAmount;
		this.tiAmount = tiAmount;
		this.medicalAllowenceAmount = medicalAllowenceAmount;
		this.arrearAmount = arrearAmount;
		this.recoveryAmount = recoveryAmount;
		this.reducedPension = reducedPension;
		this.accountNo = accountNo;
		this.trnPensionBillHdrId = trnPensionBillHdrId;
		this.pensionerName = pensionerName;
		this.allcationBf11156 = allcationBf11156;
		this.allcationAf11156 = allcationAf11156;
		this.allcationAf10560 = allcationAf10560;
		this.personalPensionAmount = personalPensionAmount;
		this.irAmount = irAmount;
		this.moCommission = moCommission;
		this.tiArrearAmount = tiArrearAmount;
		this.omr = omr;
		this.otherBenefits = otherBenefits;
		this.lta = lta;
        this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getTrnPensionBillDtlsId() {
		return this.trnPensionBillDtlsId;
	}

	public void setTrnPensionBillDtlsId(Long trnPensionBillDtlsId) {
		this.trnPensionBillDtlsId = trnPensionBillDtlsId;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
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

	public BigDecimal getCvpMonthAmount() {
		return this.cvpMonthAmount;
	}

	public void setCvpMonthAmount(BigDecimal cvpMonthAmount) {
		this.cvpMonthAmount = cvpMonthAmount;
	}

	public BigDecimal getPensionAmount() {
		return this.pensionAmount;
	}

	public void setPensionAmount(BigDecimal pensionAmount) {
		this.pensionAmount = pensionAmount;
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

	public BigDecimal getReducedPension() {
		return this.reducedPension;
	}

	public void setReducedPension(BigDecimal reducedPension) {
		this.reducedPension = reducedPension;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Long getTrnPensionBillHdrId() {
		return this.trnPensionBillHdrId;
	}

	public void setTrnPensionBillHdrId(Long trnPensionBillHdrId) {
		this.trnPensionBillHdrId = trnPensionBillHdrId;
	}

	public String getPensionerName() {
		return this.pensionerName;
	}

	public void setPensionerName(String pensionerName) {
		this.pensionerName = pensionerName;
	}

	public BigDecimal getAllcationBf11156() {
		return this.allcationBf11156;
	}

	public void setAllcationBf11156(BigDecimal allcationBf11156) {
		this.allcationBf11156 = allcationBf11156;
	}

	public BigDecimal getAllcationAf11156() {
		return this.allcationAf11156;
	}

	public void setAllcationAf11156(BigDecimal allcationAf11156) {
		this.allcationAf11156 = allcationAf11156;
	}

	public BigDecimal getAllcationAf10560() {
		return this.allcationAf10560;
	}

	public void setAllcationAf10560(BigDecimal allcationAf10560) {
		this.allcationAf10560 = allcationAf10560;
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

	public BigDecimal getMoCommission() {
		return this.moCommission;
	}

	public void setMoCommission(BigDecimal moCommission) {
		this.moCommission = moCommission;
	}

	public BigDecimal getTiArrearAmount() {
		return this.tiArrearAmount;
	}

	public void setTiArrearAmount(BigDecimal tiArrearAmount) {
		this.tiArrearAmount = tiArrearAmount;
	}

	public BigDecimal getOmr() {
		return this.omr;
	}

	public void setOmr(BigDecimal omr) {
		this.omr = omr;
	}

	public BigDecimal getOtherBenefits() {
		return this.otherBenefits;
	}

	public void setOtherBenefits(BigDecimal otherBenefits) {
		this.otherBenefits = otherBenefits;
	}

	public BigDecimal getLta() {
		return this.lta;
	}

	public void setLta(BigDecimal lta) {
		this.lta = lta;
	}
    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }

    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }

}
