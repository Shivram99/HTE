package com.tcs.sgv.pensionpay.valueobject;

// Generated Apr 23, 2008 12:42:34 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionRecoveryDtls generated by hbm2java
 */
public class TrnPensionRecoveryDtls implements java.io.Serializable, Cloneable {

	// Fields

	private Long trnPensionRecoveryDtlsId;

	private String pensionerCode;

	private String recoveryFromFlag;

	private Date dateOfReceipt;

	private String recoveryType;

	private String accountNumber;

	private String edpCode;

	private String mjrhdCode;

	private String submjrhdCode;

	private String minhdCode;

	private String subhdCode;

	private BigDecimal amount = BigDecimal.ZERO;

	private Integer fromMonth;

	private Integer toMonth;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String deductionType;

	private Long billNo;

	private Integer trnCounter;

	private String isManual;

	// --------New Fields Added
	private Integer noOfInstallments;
	
	private String nature;
	
	private String schemeCode;
	
	private Long dcrgDtlsId;
	
	private String schemeName;

	// Constructors

	/** default constructor */
	public TrnPensionRecoveryDtls() {
	}

	/** minimal constructor */
	public TrnPensionRecoveryDtls(Long trnPensionRecoveryDtlsId,
			String pensionerCode) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
		this.pensionerCode = pensionerCode;
	}

	/** full constructor */
		
	public TrnPensionRecoveryDtls(Long trnPensionRecoveryDtlsId,
			String pensionerCode, String recoveryFromFlag, Date dateOfReceipt,
			String recoveryType, String accountNumber, String edpCode,
			String mjrhdCode, String submjrhdCode, String minhdCode,
			String subhdCode, BigDecimal amount, Integer fromMonth,
			Integer toMonth, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, String deductionType, Long billNo,
			Integer trnCounter, String isManual, Integer noOfInstallments,
			String nature, String schemeCode,Long dcrgDtlsId,String schemeName) {
		super();
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
		this.pensionerCode = pensionerCode;
		this.recoveryFromFlag = recoveryFromFlag;
		this.dateOfReceipt = dateOfReceipt;
		this.recoveryType = recoveryType;
		this.accountNumber = accountNumber;
		this.edpCode = edpCode;
		this.mjrhdCode = mjrhdCode;
		this.submjrhdCode = submjrhdCode;
		this.minhdCode = minhdCode;
		this.subhdCode = subhdCode;
		this.amount = amount;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.deductionType = deductionType;
		this.billNo = billNo;
		this.trnCounter = trnCounter;
		this.isManual = isManual;
		this.noOfInstallments = noOfInstallments;
		this.nature = nature;
		this.schemeCode = schemeCode;
		this.dcrgDtlsId = dcrgDtlsId;
		this.schemeName = schemeName;
	}

	public TrnPensionRecoveryDtls(Long trnPensionRecoveryDtlsId,
			String pensionerCode, String recoveryFromFlag, 
			String recoveryType,String mjrhdCode, 
			BigDecimal amount, Integer fromMonth,
			Integer toMonth, Long billNo,Integer noOfInstallments,String nature, String schemeCode,Long dcrgDtlsId,String schemeName) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
		this.pensionerCode = pensionerCode;
		this.recoveryFromFlag = recoveryFromFlag;
		this.recoveryType = recoveryType;
		this.mjrhdCode = mjrhdCode;
		this.amount =amount;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.billNo = billNo;
		this.noOfInstallments = noOfInstallments;
		this.nature = nature;
		this.schemeCode = schemeCode;
		this.dcrgDtlsId = dcrgDtlsId;
		this.schemeName = schemeName;
	}
	public Long getTrnPensionRecoveryDtlsId() {
		return this.trnPensionRecoveryDtlsId;
	}

	public void setTrnPensionRecoveryDtlsId(Long trnPensionRecoveryDtlsId) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
	}

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getRecoveryFromFlag() {
		return this.recoveryFromFlag;
	}

	public void setRecoveryFromFlag(String recoveryFromFlag) {
		this.recoveryFromFlag = recoveryFromFlag;
	}

	public Date getDateOfReceipt() {
		return this.dateOfReceipt;
	}

	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public String getRecoveryType() {
		return this.recoveryType;
	}

	public void setRecoveryType(String recoveryType) {
		this.recoveryType = recoveryType;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}

	public String getMjrhdCode() {
		return this.mjrhdCode;
	}

	public void setMjrhdCode(String mjrhdCode) {
		this.mjrhdCode = mjrhdCode;
	}

	public String getSubmjrhdCode() {
		return this.submjrhdCode;
	}

	public void setSubmjrhdCode(String submjrhdCode) {
		this.submjrhdCode = submjrhdCode;
	}

	public String getMinhdCode() {
		return this.minhdCode;
	}

	public void setMinhdCode(String minhdCode) {
		this.minhdCode = minhdCode;
	}

	public String getSubhdCode() {
		return this.subhdCode;
	}

	public void setSubhdCode(String subhdCode) {
		this.subhdCode = subhdCode;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getFromMonth() {
		return this.fromMonth;
	}

	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Integer getToMonth() {
		return this.toMonth;
	}

	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
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

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public Long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}

	public String getIsManual() {
		return this.isManual;
	}

	public Integer getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(Integer noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	
	public Long getDcrgDtlsId() {
		return dcrgDtlsId;
	}

	public void setDcrgDtlsId(Long dcrgDtlsId) {
		this.dcrgDtlsId = dcrgDtlsId;
	}
	
	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Object clone() {

		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen
			throw new InternalError(e.toString());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdPostId == null) ? 0 : createdPostId.hashCode());
		result = prime * result
				+ ((createdUserId == null) ? 0 : createdUserId.hashCode());
		result = prime * result
				+ ((dateOfReceipt == null) ? 0 : dateOfReceipt.hashCode());
		result = prime * result
				+ ((dcrgDtlsId == null) ? 0 : dcrgDtlsId.hashCode());
		result = prime * result
				+ ((deductionType == null) ? 0 : deductionType.hashCode());
		result = prime * result + ((edpCode == null) ? 0 : edpCode.hashCode());
		result = prime * result
				+ ((fromMonth == null) ? 0 : fromMonth.hashCode());
		result = prime * result
				+ ((isManual == null) ? 0 : isManual.hashCode());
		result = prime * result
				+ ((minhdCode == null) ? 0 : minhdCode.hashCode());
		result = prime * result
				+ ((mjrhdCode == null) ? 0 : mjrhdCode.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime
				* result
				+ ((noOfInstallments == null) ? 0 : noOfInstallments.hashCode());
		result = prime * result
				+ ((pensionerCode == null) ? 0 : pensionerCode.hashCode());
		result = prime
				* result
				+ ((recoveryFromFlag == null) ? 0 : recoveryFromFlag.hashCode());
		result = prime * result
				+ ((recoveryType == null) ? 0 : recoveryType.hashCode());
		result = prime * result
				+ ((schemeCode == null) ? 0 : schemeCode.hashCode());
		result = prime * result
				+ ((schemeName == null) ? 0 : schemeName.hashCode());
		result = prime * result
				+ ((subhdCode == null) ? 0 : subhdCode.hashCode());
		result = prime * result
				+ ((submjrhdCode == null) ? 0 : submjrhdCode.hashCode());
		result = prime * result + ((toMonth == null) ? 0 : toMonth.hashCode());
		result = prime * result
				+ ((trnCounter == null) ? 0 : trnCounter.hashCode());
		result = prime
				* result
				+ ((trnPensionRecoveryDtlsId == null) ? 0
						: trnPensionRecoveryDtlsId.hashCode());
		result = prime * result
				+ ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result
				+ ((updatedPostId == null) ? 0 : updatedPostId.hashCode());
		result = prime * result
				+ ((updatedUserId == null) ? 0 : updatedUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrnPensionRecoveryDtls other = (TrnPensionRecoveryDtls) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdPostId == null) {
			if (other.createdPostId != null)
				return false;
		} else if (!createdPostId.equals(other.createdPostId))
			return false;
		if (createdUserId == null) {
			if (other.createdUserId != null)
				return false;
		} else if (!createdUserId.equals(other.createdUserId))
			return false;
		if (dateOfReceipt == null) {
			if (other.dateOfReceipt != null)
				return false;
		} else if (!dateOfReceipt.equals(other.dateOfReceipt))
			return false;
		if (dcrgDtlsId == null) {
			if (other.dcrgDtlsId != null)
				return false;
		} else if (!dcrgDtlsId.equals(other.dcrgDtlsId))
			return false;
		if (deductionType == null) {
			if (other.deductionType != null)
				return false;
		} else if (!deductionType.equals(other.deductionType))
			return false;
		if (edpCode == null) {
			if (other.edpCode != null)
				return false;
		} else if (!edpCode.equals(other.edpCode))
			return false;
		if (fromMonth == null) {
			if (other.fromMonth != null)
				return false;
		} else if (!fromMonth.equals(other.fromMonth))
			return false;
		if (isManual == null) {
			if (other.isManual != null)
				return false;
		} else if (!isManual.equals(other.isManual))
			return false;
		if (minhdCode == null) {
			if (other.minhdCode != null)
				return false;
		} else if (!minhdCode.equals(other.minhdCode))
			return false;
		if (mjrhdCode == null) {
			if (other.mjrhdCode != null)
				return false;
		} else if (!mjrhdCode.equals(other.mjrhdCode))
			return false;
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		if (noOfInstallments == null) {
			if (other.noOfInstallments != null)
				return false;
		} else if (!noOfInstallments.equals(other.noOfInstallments))
			return false;
		if (pensionerCode == null) {
			if (other.pensionerCode != null)
				return false;
		} else if (!pensionerCode.equals(other.pensionerCode))
			return false;
		if (recoveryFromFlag == null) {
			if (other.recoveryFromFlag != null)
				return false;
		} else if (!recoveryFromFlag.equals(other.recoveryFromFlag))
			return false;
		if (recoveryType == null) {
			if (other.recoveryType != null)
				return false;
		} else if (!recoveryType.equals(other.recoveryType))
			return false;
		if (schemeCode == null) {
			if (other.schemeCode != null)
				return false;
		} else if (!schemeCode.equals(other.schemeCode))
			return false;
		if (schemeName == null) {
			if (other.schemeName != null)
				return false;
		} else if (!schemeName.equals(other.schemeName))
			return false;
		if (subhdCode == null) {
			if (other.subhdCode != null)
				return false;
		} else if (!subhdCode.equals(other.subhdCode))
			return false;
		if (submjrhdCode == null) {
			if (other.submjrhdCode != null)
				return false;
		} else if (!submjrhdCode.equals(other.submjrhdCode))
			return false;
		if (toMonth == null) {
			if (other.toMonth != null)
				return false;
		} else if (!toMonth.equals(other.toMonth))
			return false;
		if (trnCounter == null) {
			if (other.trnCounter != null)
				return false;
		} else if (!trnCounter.equals(other.trnCounter))
			return false;
		if (trnPensionRecoveryDtlsId == null) {
			if (other.trnPensionRecoveryDtlsId != null)
				return false;
		} else if (!trnPensionRecoveryDtlsId
				.equals(other.trnPensionRecoveryDtlsId))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (updatedPostId == null) {
			if (other.updatedPostId != null)
				return false;
		} else if (!updatedPostId.equals(other.updatedPostId))
			return false;
		if (updatedUserId == null) {
			if (other.updatedUserId != null)
				return false;
		} else if (!updatedUserId.equals(other.updatedUserId))
			return false;
		return true;
	}
	
}
