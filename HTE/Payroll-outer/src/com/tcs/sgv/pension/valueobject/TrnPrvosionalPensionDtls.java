// default package
// Generated Feb 6, 2008 7:51:25 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.pension.valueobject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPrvosionalPensionDtls generated by hbm2java
 */
public class TrnPrvosionalPensionDtls implements java.io.Serializable
{

    // Fields    

    private Long prvosionalPensionDtlsId;

    private String pensionerCode;

    private String ppoNo;

    private Date commensionDate;

    private BigDecimal dcrgAmount;

    private Date dcrgDate;

    private BigDecimal basicPensionAmount;

    private Date paidDate;

    private Date fp1Date;

    private BigDecimal fp1Amount;

    private Date fp2Date;

    private BigDecimal fp2Amount;

    private BigDecimal trnCounter;

    private BigDecimal dbId;

    private String locationCode;

    private BigDecimal createdUserId;

    private BigDecimal createdPostId;

    private Date createdDate;

    private BigDecimal updatedUserId;

    private BigDecimal updatedPostId;

    private Date updatedDate;

    private BigDecimal pensionRequestId;


    // Constructors

    /** default constructor */
    public TrnPrvosionalPensionDtls()
    {
    }


    /** minimal constructor */
    public TrnPrvosionalPensionDtls(Long prvosionalPensionDtlsId, BigDecimal pensionRequestId)
    {
        this.prvosionalPensionDtlsId = prvosionalPensionDtlsId;
        this.pensionRequestId = pensionRequestId;
    }


    /** full constructor */
    public TrnPrvosionalPensionDtls(Long prvosionalPensionDtlsId, String pensionerCode, String ppoNo,
            Date commensionDate, BigDecimal dcrgAmount, Date dcrgDate, BigDecimal basicPensionAmount, Date paidDate,
            Date fp1Date, BigDecimal fp1Amount, Date fp2Date, BigDecimal fp2Amount, BigDecimal trnCounter,
            BigDecimal dbId, String locationCode, BigDecimal createdUserId, BigDecimal createdPostId,
            Date createdDate, BigDecimal updatedUserId, BigDecimal updatedPostId, Date updatedDate,
            BigDecimal pensionRequestId)
    {
        this.prvosionalPensionDtlsId = prvosionalPensionDtlsId;
        this.pensionerCode = pensionerCode;
        this.ppoNo = ppoNo;
        this.commensionDate = commensionDate;
        this.dcrgAmount = dcrgAmount;
        this.dcrgDate = dcrgDate;
        this.basicPensionAmount = basicPensionAmount;
        this.paidDate = paidDate;
        this.fp1Date = fp1Date;
        this.fp1Amount = fp1Amount;
        this.fp2Date = fp2Date;
        this.fp2Amount = fp2Amount;
        this.trnCounter = trnCounter;
        this.dbId = dbId;
        this.locationCode = locationCode;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.pensionRequestId = pensionRequestId;
    }


    // Property accessors
    public Long getPrvosionalPensionDtlsId()
    {
        return this.prvosionalPensionDtlsId;
    }


    public void setPrvosionalPensionDtlsId(Long prvosionalPensionDtlsId)
    {
        this.prvosionalPensionDtlsId = prvosionalPensionDtlsId;
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


    public Date getCommensionDate()
    {
        return this.commensionDate;
    }


    public void setCommensionDate(Date commensionDate)
    {
        this.commensionDate = commensionDate;
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


    public BigDecimal getBasicPensionAmount()
    {
        return this.basicPensionAmount;
    }


    public void setBasicPensionAmount(BigDecimal basicPensionAmount)
    {
        this.basicPensionAmount = basicPensionAmount;
    }


    public Date getPaidDate()
    {
        return this.paidDate;
    }


    public void setPaidDate(Date paidDate)
    {
        this.paidDate = paidDate;
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


    public BigDecimal getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(BigDecimal trnCounter)
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


    public BigDecimal getPensionRequestId()
    {
        return this.pensionRequestId;
    }


    public void setPensionRequestId(BigDecimal pensionRequestId)
    {
        this.pensionRequestId = pensionRequestId;
    }

}
