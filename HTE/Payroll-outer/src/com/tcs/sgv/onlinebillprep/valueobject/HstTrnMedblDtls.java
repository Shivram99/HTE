// default package
// Generated Sep 26, 2007 7:51:19 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.onlinebillprep.valueobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnMedblDtls generated by hbm2java
 */
public class HstTrnMedblDtls implements java.io.Serializable
{

    // Fields    

    private HstTrnMedblDtlsId id;

    private Long trnMedblHdrId;

    private String ptntName;

    private String rltnshp;

    private BigDecimal claimAmt;

    private String remarks;

    private Long createdUserId;

    private Long createdPostId;

    private Date createdDate;

    private Long updatedUserId;

    private Long updatedPostId;

    private Date updatedDate;

    private Long dbId;

    private String trtmtTime;

    private String locationCode;


    // Constructors

    /** default constructor */
    public HstTrnMedblDtls()
    {
    }


    /** minimal constructor */
    public HstTrnMedblDtls(HstTrnMedblDtlsId id, Long trnMedblHdrId, String ptntName, String rltnshp,
            BigDecimal claimAmt, String remarks, Long createdUserId, Long createdPostId,
            Date createdDate, Long dbId, String locationCode)
    {
        this.id = id;
        this.trnMedblHdrId = trnMedblHdrId;
        this.ptntName = ptntName;
        this.rltnshp = rltnshp;
        this.claimAmt = claimAmt;
        this.remarks = remarks;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.dbId = dbId;
        this.locationCode = locationCode;
    }


    /** full constructor */
    public HstTrnMedblDtls(HstTrnMedblDtlsId id, Long trnMedblHdrId, String ptntName, String rltnshp,
            BigDecimal claimAmt, String remarks, Long createdUserId, Long createdPostId,
            Date createdDate, Long updatedUserId, Long updatedPostId, Date updatedDate, Long dbId,
            String trtmtTime, String locationCode)
    {
        this.id = id;
        this.trnMedblHdrId = trnMedblHdrId;
        this.ptntName = ptntName;
        this.rltnshp = rltnshp;
        this.claimAmt = claimAmt;
        this.remarks = remarks;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.dbId = dbId;
        this.trtmtTime = trtmtTime;
        this.locationCode = locationCode;
    }


    // Property accessors
    public HstTrnMedblDtlsId getId()
    {
        return this.id;
    }


    public void setId(HstTrnMedblDtlsId id)
    {
        this.id = id;
    }


    public Long getTrnMedblHdrId()
    {
        return this.trnMedblHdrId;
    }


    public void setTrnMedblHdrId(Long trnMedblHdrId)
    {
        this.trnMedblHdrId = trnMedblHdrId;
    }


    public String getPtntName()
    {
        return this.ptntName;
    }


    public void setPtntName(String ptntName)
    {
        this.ptntName = ptntName;
    }


    public String getRltnshp()
    {
        return this.rltnshp;
    }


    public void setRltnshp(String rltnshp)
    {
        this.rltnshp = rltnshp;
    }


    public BigDecimal getClaimAmt()
    {
        return this.claimAmt;
    }


    public void setClaimAmt(BigDecimal claimAmt)
    {
        this.claimAmt = claimAmt;
    }


    public String getRemarks()
    {
        return this.remarks;
    }


    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }


    public Long getCreatedUserId()
    {
        return this.createdUserId;
    }


    public void setCreatedUserId(Long createdUserId)
    {
        this.createdUserId = createdUserId;
    }


    public Long getCreatedPostId()
    {
        return this.createdPostId;
    }


    public void setCreatedPostId(Long createdPostId)
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


    public Long getUpdatedUserId()
    {
        return this.updatedUserId;
    }


    public void setUpdatedUserId(Long updatedUserId)
    {
        this.updatedUserId = updatedUserId;
    }


    public Long getUpdatedPostId()
    {
        return this.updatedPostId;
    }


    public void setUpdatedPostId(Long updatedPostId)
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


    public Long getDbId()
    {
        return this.dbId;
    }


    public void setDbId(Long dbId)
    {
        this.dbId = dbId;
    }


    public String getTrtmtTime()
    {
        return this.trtmtTime;
    }


    public void setTrtmtTime(String trtmtTime)
    {
        this.trtmtTime = trtmtTime;
    }


    public String getLocationCode()
    {
        return this.locationCode;
    }


    public void setLocationCode(String locationCode)
    {
        this.locationCode = locationCode;
    }

}
