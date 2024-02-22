package com.tcs.sgv.common.valueobject;

// Generated Aug 3, 2007 3:39:11 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

import com.tcs.sgv.common.constant.DBConstants;

/**
 * TrnBillEdpDtls1 generated by hbm2java
 */
public class TrnBillEdpDtls implements java.io.Serializable {

	// Fields

	private long billEdpId;

	private long billNo;

	private Long typeEdpId;

	private BigDecimal edpAmt;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private String locationCode;

	private Long dbId;

	private Integer trnCounter;

	private String addDedFlag;

	private String edpCategory;

	private String expRcpRec;

	private String edpCode;

	private String autoAdd;
	
	private String budmjrhdCode;

	private String budsubmjrhdCode;

	private String budminhdCode;

	private String budsubhdCode;

	private String buddtlhdCode;

	private String budobjhdCode;

	private String edpDesc;
	// Constructors

	public String getEdpDesc() {
		return edpDesc;
	}

	public void setEdpDesc(String edpDesc) {
		this.edpDesc = edpDesc;
	}

	public String getBudmjrhdCode() {
		return budmjrhdCode;
	}

	public void setBudmjrhdCode(String budmjrhdCode) {
		this.budmjrhdCode = budmjrhdCode;
	}

	public String getBudsubmjrhdCode() {
		return budsubmjrhdCode;
	}

	public void setBudsubmjrhdCode(String budsubmjrhdCode) {
		this.budsubmjrhdCode = budsubmjrhdCode;
	}

	public String getBudminhdCode() {
		return budminhdCode;
	}

	public void setBudminhdCode(String budminhdCode) {
		this.budminhdCode = budminhdCode;
	}

	public String getBudsubhdCode() {
		return budsubhdCode;
	}

	public void setBudsubhdCode(String budsubhdCode) {
		this.budsubhdCode = budsubhdCode;
	}

	public String getBuddtlhdCode() {
		return buddtlhdCode;
	}

	public void setBuddtlhdCode(String buddtlhdCode) {
		this.buddtlhdCode = buddtlhdCode;
	}

	public String getBudobjhdCode() 
	{
		try
		{
			if(getExpRcpRec() !=null && getExpRcpRec().equals(DBConstants.EDP_REC) && getEdpCode()!=null && getEdpCode().equals(DBConstants.EDPCODE5701))
			{
				return "7057";
			}
			else if(getExpRcpRec() !=null && getExpRcpRec().equals(DBConstants.EDP_REC) && getEdpCode()!=null && getEdpCode().equals(DBConstants.EDPCODE5801))
			{
				return "7058";
			}
			else
				return budobjhdCode;
		}
		catch (Exception e) 
		{
			return budobjhdCode;
		}
	}

	public void setBudobjhdCode(String budobjhdCode) {
		this.budobjhdCode = budobjhdCode;
	}

	/** default constructor */
	public TrnBillEdpDtls() {
	}

	/** minimal constructor */
	public TrnBillEdpDtls(long billEdpId, long billNo, String locationCode,
			Long createdUserId, Long createdPostId, Date createdDate) {
		this.billEdpId = billEdpId;
		this.billNo = billNo;
		this.locationCode = locationCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnBillEdpDtls(long billEdpId, long billNo, Long typeEdpId,
			BigDecimal edpAmt, Long createdUserId,
			Long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, String locationCode,
			Long dbId, Integer trnCounter, String addDedFlag,
			String edpCategory, String expRcpRec, String edpCode, String autoAdd) {
		this.billEdpId = billEdpId;
		this.billNo = billNo;
		this.typeEdpId = typeEdpId;
		this.edpAmt = edpAmt;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locationCode = locationCode;
		this.dbId = dbId;
		this.trnCounter = trnCounter;
		this.addDedFlag = addDedFlag;
		this.edpCategory = edpCategory;
		this.expRcpRec = expRcpRec;
		this.edpCode = edpCode;
		this.autoAdd = autoAdd;
	}

	// Property accessors
	public long getBillEdpId() {
		return this.billEdpId;
	}

	public void setBillEdpId(long billEdpId) {
		this.billEdpId = billEdpId;
	}

	public long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}

	public Long getTypeEdpId() {
		return this.typeEdpId;
	}

	public void setTypeEdpId(Long typeEdpId) {
		this.typeEdpId = typeEdpId;
	}

	public BigDecimal getEdpAmt() {
		return this.edpAmt;
	}

	public void setEdpAmt(BigDecimal edpAmt) {
		this.edpAmt = edpAmt;
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

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public String getAddDedFlag() {
		return this.addDedFlag;
	}

	public void setAddDedFlag(String addDedFlag) {
		this.addDedFlag = addDedFlag;
	}

	public String getEdpCategory() {
		return this.edpCategory;
	}

	public void setEdpCategory(String edpCategory) {
		this.edpCategory = edpCategory;
	}

	public String getExpRcpRec() {
		return this.expRcpRec;
	}

	public void setExpRcpRec(String expRcpRec) {
		this.expRcpRec = expRcpRec;
	}

	public String getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}

	public String getAutoAdd() {
		return this.autoAdd;
	}

	public void setAutoAdd(String autoAdd) {
		this.autoAdd = autoAdd;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
