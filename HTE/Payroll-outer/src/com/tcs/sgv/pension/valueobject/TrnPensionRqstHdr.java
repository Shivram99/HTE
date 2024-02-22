package com.tcs.sgv.pension.valueobject;

// default package
// Generated Mar 1, 2008 10:35:39 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionRqstHdr generated by hbm2java
 */
public class TrnPensionRqstHdr implements java.io.Serializable, Cloneable
{

    // Fields

    private Long pensionRequestId;

    private String pensionerCode;

    private String ppoNo;

    private Date ppoDate;

    private Date ppoInwardDate;

    private String inwardMode;

    private String pensionType;

    private String schemeType;

    private String status;

    private String remark;

    private BigDecimal headCode;

    private Date commensionDate;

    private String cvpOrderNo;

    private BigDecimal cvpAmount;

    private Date cvpDate;

    private String dcrgOrderNo;

    private BigDecimal dcrgAmount;

    private Date dcrgDate;

    private Date paidDate;

    private BigDecimal pensionableAmount;

    private BigDecimal dppfAmount;

    private BigDecimal basicPensionAmount;

    private BigDecimal dpPercent;

    private BigDecimal tiPercent;

    private BigDecimal cvpMonthlyAmount;

    private BigDecimal medicalAllowenceAmount;

    private Date fp1Date;

    private BigDecimal fp1Amount;

    private Date fp2Date;

    private BigDecimal fp2Amount;

    private String typeFlag;

    private Date cvpRestorationDate;

    private String approveStatus;

    private String caseStatus;

    private BigDecimal totalRecovery;

    private BigDecimal pendingRecovery;

    private Integer trnCounter;

    private BigDecimal dbId;

    private String locationCode;

    private BigDecimal createdUserId;

    private BigDecimal createdPostId;

    private Date createdDate;

    private BigDecimal updatedUserId;

    private BigDecimal updatedPostId;

    private Date updatedDate;

    private BigDecimal caseOwner;

    private String auditorFlag;

    private String totalSrvc;

    private String dcrgPaidFlag;

    private String cvpPaidFlag;

    private String deptOrdNo;

    private BigDecimal orgBf11156;

    private BigDecimal redBf11156;

    private BigDecimal orgAf11156;

    private BigDecimal redAf11156;

    private BigDecimal orgAf10560;

    private BigDecimal redAf10560;

    private String authority;

    private String sanctionLetterNo;

    private BigDecimal personalPension;

    private BigDecimal ir;

    private Date closedDate;

    private Date endDate;

    private String specialCase;

    private String secondPpoNo;

    private String form22Issued;

    private String form22IssuedAuth;

    private Date form22IssuedDate;

    private String lpcIssued;

    private String lpcIssuedAuth;

    private Date lpcIssuedDate;

    private BigDecimal daPercent;

    private String caseRegisterNo;

    private Integer currCaseStatus;
    
    private String isRop;
    
    private String calcType;
    
    private Date caseRegDate;
    // Constructors

    /** default constructor */
    public TrnPensionRqstHdr()
    {
    }


    /** minimal constructor */
    public TrnPensionRqstHdr(Long pensionRequestId, String ppoNo, BigDecimal dbId, String locationCode,
            BigDecimal createdUserId, BigDecimal createdPostId, Date createdDate)
    {
        this.pensionRequestId = pensionRequestId;
        this.ppoNo = ppoNo;
        this.dbId = dbId;
        this.locationCode = locationCode;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
    }


    /** full constructor */
    public TrnPensionRqstHdr(Long pensionRequestId, String pensionerCode, String ppoNo, Date ppoDate,
            Date ppoInwardDate, String inwardMode, String pensionType, String schemeType, String status,
            String remark, BigDecimal headCode, Date commensionDate, String cvpOrderNo, BigDecimal cvpAmount,
            Date cvpDate, String dcrgOrderNo, BigDecimal dcrgAmount, Date dcrgDate, Date paidDate,
            BigDecimal pensionableAmount, BigDecimal dppfAmount, BigDecimal basicPensionAmount, BigDecimal dpPercent,
            BigDecimal tiPercent, BigDecimal cvpMonthlyAmount, BigDecimal medicalAllowenceAmount, Date fp1Date,
            BigDecimal fp1Amount, Date fp2Date, BigDecimal fp2Amount, String typeFlag, Date cvpRestorationDate,
            String approveStatus, String caseStatus, BigDecimal totalRecovery, BigDecimal pendingRecovery,
            Integer trnCounter, BigDecimal dbId, String locationCode, BigDecimal createdUserId,
            BigDecimal createdPostId, Date createdDate, BigDecimal updatedUserId, BigDecimal updatedPostId,
            Date updatedDate, BigDecimal caseOwner, String auditorFlag, String totalSrvc, String dcrgPaidFlag,
            String cvpPaidFlag, String deptOrdNo, BigDecimal orgBf11156, BigDecimal redBf11156,
            BigDecimal orgAf11156, BigDecimal redAf11156, BigDecimal orgAf10560, BigDecimal redAf10560,
            String authority, String sanctionLetterNo, BigDecimal personalPension, BigDecimal ir, Date closedDate,
            Date endDate, String specialCase, String secondPpoNo, String form22Issued, String form22IssuedAuth,
            Date form22IssuedDate, String lpcIssued, String lpcIssuedAuth, Date lpcIssuedDate, BigDecimal daPercent,
            String caseRegisterNo,Integer currCaseStatus,String isRop,Date caseRegDate)
    {
        this.pensionRequestId = pensionRequestId;
        this.pensionerCode = pensionerCode;
        this.ppoNo = ppoNo;
        this.ppoDate = ppoDate;
        this.ppoInwardDate = ppoInwardDate;
        this.inwardMode = inwardMode;
        this.pensionType = pensionType;
        this.schemeType = schemeType;
        this.status = status;
        this.remark = remark;
        this.headCode = headCode;
        this.commensionDate = commensionDate;
        this.cvpOrderNo = cvpOrderNo;
        this.cvpAmount = cvpAmount;
        this.cvpDate = cvpDate;
        this.dcrgOrderNo = dcrgOrderNo;
        this.dcrgAmount = dcrgAmount;
        this.dcrgDate = dcrgDate;
        this.paidDate = paidDate;
        this.pensionableAmount = pensionableAmount;
        this.dppfAmount = dppfAmount;
        this.basicPensionAmount = basicPensionAmount;
        this.dpPercent = dpPercent;
        this.tiPercent = tiPercent;
        this.cvpMonthlyAmount = cvpMonthlyAmount;
        this.medicalAllowenceAmount = medicalAllowenceAmount;
        this.fp1Date = fp1Date;
        this.fp1Amount = fp1Amount;
        this.fp2Date = fp2Date;
        this.fp2Amount = fp2Amount;
        this.typeFlag = typeFlag;
        this.cvpRestorationDate = cvpRestorationDate;
        this.approveStatus = approveStatus;
        this.caseStatus = caseStatus;
        this.totalRecovery = totalRecovery;
        this.pendingRecovery = pendingRecovery;
        this.trnCounter = trnCounter;
        this.dbId = dbId;
        this.locationCode = locationCode;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.caseOwner = caseOwner;
        this.auditorFlag = auditorFlag;
        this.totalSrvc = totalSrvc;
        this.dcrgPaidFlag = dcrgPaidFlag;
        this.cvpPaidFlag = cvpPaidFlag;
        this.deptOrdNo = deptOrdNo;
        this.orgBf11156 = orgBf11156;
        this.redBf11156 = redBf11156;
        this.orgAf11156 = orgAf11156;
        this.redAf11156 = redAf11156;
        this.orgAf10560 = orgAf10560;
        this.redAf10560 = redAf10560;
        this.authority = authority;
        this.sanctionLetterNo = sanctionLetterNo;
        this.personalPension = personalPension;
        this.ir = ir;
        this.closedDate = closedDate;
        this.endDate = endDate;
        this.specialCase = specialCase;
        this.secondPpoNo = secondPpoNo;
        this.form22Issued = form22Issued;
        this.form22IssuedAuth = form22IssuedAuth;
        this.form22IssuedDate = form22IssuedDate;
        this.lpcIssued = lpcIssued;
        this.lpcIssuedAuth = lpcIssuedAuth;
        this.lpcIssuedDate = lpcIssuedDate;
        this.daPercent = daPercent;
        this.caseRegisterNo = caseRegisterNo;
        this.currCaseStatus = currCaseStatus;
        this.isRop = isRop;
        this.caseRegDate = caseRegDate;
    }


    // Property accessors
    public Long getPensionRequestId()
    {
        return this.pensionRequestId;
    }


    public void setPensionRequestId(Long pensionRequestId)
    {
        this.pensionRequestId = pensionRequestId;
    }


    public String getPensionerCode()
    {
        return this.pensionerCode;
    }


    public void setPensionerCode(String pensionerCode)
    {
        this.pensionerCode = pensionerCode;
    }


    public String getPpoNo()
    {
        return this.ppoNo;
    }


    public void setPpoNo(String ppoNo)
    {
        this.ppoNo = ppoNo;
    }


    public Date getPpoDate()
    {
        return this.ppoDate;
    }


    public void setPpoDate(Date ppoDate)
    {
        this.ppoDate = ppoDate;
    }


    public Date getPpoInwardDate()
    {
        return this.ppoInwardDate;
    }


    public void setPpoInwardDate(Date ppoInwardDate)
    {
        this.ppoInwardDate = ppoInwardDate;
    }


    public String getInwardMode()
    {
        return this.inwardMode;
    }


    public void setInwardMode(String inwardMode)
    {
        this.inwardMode = inwardMode;
    }


    public String getPensionType()
    {
        return this.pensionType;
    }


    public void setPensionType(String pensionType)
    {
        this.pensionType = pensionType;
    }


    public String getSchemeType()
    {
        return this.schemeType;
    }


    public void setSchemeType(String schemeType)
    {
        this.schemeType = schemeType;
    }


    public String getStatus()
    {
        return this.status;
    }


    public void setStatus(String status)
    {
        this.status = status;
    }


    public String getRemark()
    {
        return this.remark;
    }


    public void setRemark(String remark)
    {
        this.remark = remark;
    }


    public BigDecimal getHeadCode()
    {
        return this.headCode;
    }


    public void setHeadCode(BigDecimal headCode)
    {
        this.headCode = headCode;
    }


    public Date getCommensionDate()
    {
        return this.commensionDate;
    }


    public void setCommensionDate(Date commensionDate)
    {
        this.commensionDate = commensionDate;
    }


    public String getCvpOrderNo()
    {
        return this.cvpOrderNo;
    }


    public void setCvpOrderNo(String cvpOrderNo)
    {
        this.cvpOrderNo = cvpOrderNo;
    }


    public BigDecimal getCvpAmount()
    {
        return this.cvpAmount;
    }


    public void setCvpAmount(BigDecimal cvpAmount)
    {
        this.cvpAmount = cvpAmount;
    }


    public Date getCvpDate()
    {
        return this.cvpDate;
    }


    public void setCvpDate(Date cvpDate)
    {
        this.cvpDate = cvpDate;
    }


    public String getDcrgOrderNo()
    {
        return this.dcrgOrderNo;
    }


    public void setDcrgOrderNo(String dcrgOrderNo)
    {
        this.dcrgOrderNo = dcrgOrderNo;
    }


    public BigDecimal getDcrgAmount()
    {
        return this.dcrgAmount;
    }


    public void setDcrgAmount(BigDecimal dcrgAmount)
    {
        this.dcrgAmount = dcrgAmount;
    }


    public Date getDcrgDate()
    {
        return this.dcrgDate;
    }


    public void setDcrgDate(Date dcrgDate)
    {
        this.dcrgDate = dcrgDate;
    }


    public Date getPaidDate()
    {
        return this.paidDate;
    }


    public void setPaidDate(Date paidDate)
    {
        this.paidDate = paidDate;
    }


    public BigDecimal getPensionableAmount()
    {
        return this.pensionableAmount;
    }


    public void setPensionableAmount(BigDecimal pensionableAmount)
    {
        this.pensionableAmount = pensionableAmount;
    }


    public BigDecimal getDppfAmount()
    {
        return this.dppfAmount;
    }


    public void setDppfAmount(BigDecimal dppfAmount)
    {
        this.dppfAmount = dppfAmount;
    }


    public BigDecimal getBasicPensionAmount()
    {
        return this.basicPensionAmount;
    }


    public void setBasicPensionAmount(BigDecimal basicPensionAmount)
    {
        this.basicPensionAmount = basicPensionAmount;
    }


    public BigDecimal getDpPercent()
    {
        return this.dpPercent;
    }


    public void setDpPercent(BigDecimal dpPercent)
    {
        this.dpPercent = dpPercent;
    }


    public BigDecimal getTiPercent()
    {
        return this.tiPercent;
    }


    public void setTiPercent(BigDecimal tiPercent)
    {
        this.tiPercent = tiPercent;
    }


    public BigDecimal getCvpMonthlyAmount()
    {
        return this.cvpMonthlyAmount;
    }


    public void setCvpMonthlyAmount(BigDecimal cvpMonthlyAmount)
    {
        this.cvpMonthlyAmount = cvpMonthlyAmount;
    }


    public BigDecimal getMedicalAllowenceAmount()
    {
        return this.medicalAllowenceAmount;
    }


    public void setMedicalAllowenceAmount(BigDecimal medicalAllowenceAmount)
    {
        this.medicalAllowenceAmount = medicalAllowenceAmount;
    }


    public Date getFp1Date()
    {
        return this.fp1Date;
    }


    public void setFp1Date(Date fp1Date)
    {
        this.fp1Date = fp1Date;
    }


    public BigDecimal getFp1Amount()
    {
        return this.fp1Amount;
    }


    public void setFp1Amount(BigDecimal fp1Amount)
    {
        this.fp1Amount = fp1Amount;
    }


    public Date getFp2Date()
    {
        return this.fp2Date;
    }


    public void setFp2Date(Date fp2Date)
    {
        this.fp2Date = fp2Date;
    }


    public BigDecimal getFp2Amount()
    {
        return this.fp2Amount;
    }


    public void setFp2Amount(BigDecimal fp2Amount)
    {
        this.fp2Amount = fp2Amount;
    }


    public String getTypeFlag()
    {
        return this.typeFlag;
    }


    public void setTypeFlag(String typeFlag)
    {
        this.typeFlag = typeFlag;
    }


    public Date getCvpRestorationDate()
    {
        return this.cvpRestorationDate;
    }


    public void setCvpRestorationDate(Date cvpRestorationDate)
    {
        this.cvpRestorationDate = cvpRestorationDate;
    }


    public String getApproveStatus()
    {
        return this.approveStatus;
    }


    public void setApproveStatus(String approveStatus)
    {
        this.approveStatus = approveStatus;
    }


    public String getCaseStatus()
    {
        return this.caseStatus;
    }


    public void setCaseStatus(String caseStatus)
    {
        this.caseStatus = caseStatus;
    }


    public BigDecimal getTotalRecovery()
    {
        return this.totalRecovery;
    }


    public void setTotalRecovery(BigDecimal totalRecovery)
    {
        this.totalRecovery = totalRecovery;
    }


    public BigDecimal getPendingRecovery()
    {
        return this.pendingRecovery;
    }


    public void setPendingRecovery(BigDecimal pendingRecovery)
    {
        this.pendingRecovery = pendingRecovery;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }


    public BigDecimal getDbId()
    {
        return this.dbId;
    }


    public void setDbId(BigDecimal dbId)
    {
        this.dbId = dbId;
    }


    public String getLocationCode()
    {
        return this.locationCode;
    }


    public void setLocationCode(String locationCode)
    {
        this.locationCode = locationCode;
    }


    public BigDecimal getCreatedUserId()
    {
        return this.createdUserId;
    }


    public void setCreatedUserId(BigDecimal createdUserId)
    {
        this.createdUserId = createdUserId;
    }


    public BigDecimal getCreatedPostId()
    {
        return this.createdPostId;
    }


    public void setCreatedPostId(BigDecimal createdPostId)
    {
        this.createdPostId = createdPostId;
    }


    public Date getCreatedDate()
    {
        return this.createdDate;
    }


    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }


    public BigDecimal getUpdatedUserId()
    {
        return this.updatedUserId;
    }


    public void setUpdatedUserId(BigDecimal updatedUserId)
    {
        this.updatedUserId = updatedUserId;
    }


    public BigDecimal getUpdatedPostId()
    {
        return this.updatedPostId;
    }


    public void setUpdatedPostId(BigDecimal updatedPostId)
    {
        this.updatedPostId = updatedPostId;
    }


    public Date getUpdatedDate()
    {
        return this.updatedDate;
    }


    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }


    public BigDecimal getCaseOwner()
    {
        return this.caseOwner;
    }


    public void setCaseOwner(BigDecimal caseOwner)
    {
        this.caseOwner = caseOwner;
    }


    public String getAuditorFlag()
    {
        return this.auditorFlag;
    }


    public void setAuditorFlag(String auditorFlag)
    {
        this.auditorFlag = auditorFlag;
    }


    public String getTotalSrvc()
    {
        return this.totalSrvc;
    }


    public void setTotalSrvc(String totalSrvc)
    {
        this.totalSrvc = totalSrvc;
    }


    public String getDcrgPaidFlag()
    {
        return this.dcrgPaidFlag;
    }


    public void setDcrgPaidFlag(String dcrgPaidFlag)
    {
        this.dcrgPaidFlag = dcrgPaidFlag;
    }


    public String getCvpPaidFlag()
    {
        return this.cvpPaidFlag;
    }


    public void setCvpPaidFlag(String cvpPaidFlag)
    {
        this.cvpPaidFlag = cvpPaidFlag;
    }


    public String getDeptOrdNo()
    {
        return this.deptOrdNo;
    }


    public void setDeptOrdNo(String deptOrdNo)
    {
        this.deptOrdNo = deptOrdNo;
    }


    public BigDecimal getOrgBf11156()
    {
        return this.orgBf11156;
    }


    public void setOrgBf11156(BigDecimal orgBf11156)
    {
        this.orgBf11156 = orgBf11156;
    }


    public BigDecimal getRedBf11156()
    {
        return this.redBf11156;
    }


    public void setRedBf11156(BigDecimal redBf11156)
    {
        this.redBf11156 = redBf11156;
    }


    public BigDecimal getOrgAf11156()
    {
        return this.orgAf11156;
    }


    public void setOrgAf11156(BigDecimal orgAf11156)
    {
        this.orgAf11156 = orgAf11156;
    }


    public BigDecimal getRedAf11156()
    {
        return this.redAf11156;
    }


    public void setRedAf11156(BigDecimal redAf11156)
    {
        this.redAf11156 = redAf11156;
    }


    public BigDecimal getOrgAf10560()
    {
        return this.orgAf10560;
    }


    public void setOrgAf10560(BigDecimal orgAf10560)
    {
        this.orgAf10560 = orgAf10560;
    }


    public BigDecimal getRedAf10560()
    {
        return this.redAf10560;
    }


    public void setRedAf10560(BigDecimal redAf10560)
    {
        this.redAf10560 = redAf10560;
    }


    public String getAuthority()
    {
        return this.authority;
    }


    public void setAuthority(String authority)
    {
        this.authority = authority;
    }


    public String getSanctionLetterNo()
    {
        return this.sanctionLetterNo;
    }


    public void setSanctionLetterNo(String sanctionLetterNo)
    {
        this.sanctionLetterNo = sanctionLetterNo;
    }


    public BigDecimal getPersonalPension()
    {
        return this.personalPension;
    }


    public void setPersonalPension(BigDecimal personalPension)
    {
        this.personalPension = personalPension;
    }


    public BigDecimal getIr()
    {
        return this.ir;
    }


    public void setIr(BigDecimal ir)
    {
        this.ir = ir;
    }


    public Date getClosedDate()
    {
        return this.closedDate;
    }


    public void setClosedDate(Date closedDate)
    {
        this.closedDate = closedDate;
    }


    public Date getEndDate()
    {
        return this.endDate;
    }


    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }


    public String getSpecialCase()
    {
        return this.specialCase;
    }


    public void setSpecialCase(String specialCase)
    {
        this.specialCase = specialCase;
    }


    public String getSecondPpoNo()
    {
        return this.secondPpoNo;
    }


    public void setSecondPpoNo(String secondPpoNo)
    {
        this.secondPpoNo = secondPpoNo;
    }


    public void setLpcIssued(String LpcIssued)
    {
        this.lpcIssued = LpcIssued;
    }


    public void setLpcIssuedAuth(String LpcIssuedAuth)
    {
        this.lpcIssuedAuth = LpcIssuedAuth;
    }


    public void setLpcIssuedDate(Date LpcIssuedDate)
    {
        this.lpcIssuedDate = LpcIssuedDate;
    }


    public void setForm22IssuedDate(Date Form22IssuedDate)
    {
        this.form22IssuedDate = Form22IssuedDate;
    }


    public void setForm22IssuedAuth(String Form22IssuedAuth)
    {
        this.form22IssuedAuth = Form22IssuedAuth;
    }


    public void setForm22Issued(String Form22Issued)
    {
        this.form22Issued = Form22Issued;
    }


    public String getLpcIssued()
    {
        return this.lpcIssued;
    }


    public String getLpcIssuedAuth()
    {
        return this.lpcIssuedAuth;
    }


    public Date getLpcIssuedDate()
    {
        return this.lpcIssuedDate;
    }


    public Date getForm22IssuedDate()
    {
        return this.form22IssuedDate;
    }


    public String getForm22IssuedAuth()
    {
        return this.form22IssuedAuth;
    }


    public String getForm22Issued()
    {
        return this.form22Issued;
    }


    public BigDecimal getDaPercent()
    {
        return daPercent;
    }


    public String getCaseRegisterNo()
    {
        return caseRegisterNo;
    }


    public void setDaPercent(BigDecimal daPercent)
    {
        this.daPercent = daPercent;
    }


    public void setCaseRegisterNo(String caseRegisterNo)
    {
        this.caseRegisterNo = caseRegisterNo;
    }
    public void setcurrCaseStatus(Integer currCaseStatus)
    {
    	this.currCaseStatus = currCaseStatus;
    }
    public Integer getCurrCaseStatus()
    {
    	return this.currCaseStatus;
    }
    
    public String getCalcType()
    {
    	return this.calcType;
    }
    
    public void setCalcType(String calcType)
    {
    	this.calcType = calcType;
    }
    public String getIsRop()
    {
    	return this.isRop;
    }
    
    public void setIsRop(String isRop)
    {
    	this.isRop = isRop;
    }
    
    public Date getCaseRegDate()
    {
    	return caseRegDate;
    }
    
    public void setCaseRegDate(Date caseRegDate)
    {
    	this.caseRegDate = caseRegDate;
    }
    
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
