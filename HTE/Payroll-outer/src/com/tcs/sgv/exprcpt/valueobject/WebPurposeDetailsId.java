package com.tcs.sgv.exprcpt.valueobject;

// Generated Oct 23, 2007 4:22:01 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * WebPurposeDetailsId generated by hbm2java
 */
public class WebPurposeDetailsId implements java.io.Serializable
{

    // Fields    

    private String wpdChallanId;

    private String wpdMajorHead;

    private String wpdSubMajorHead;

    private String wpdMinorHead;

    private String wpdSubMinorHead;

    private String wpdDetailHead;

    private String wpdAmount;

    private String wpdCreatedBy;

    private Date wpdCreatedDt;

    private String wpdUpdatedBy;

    private Date wpdUpdatedDt;


    // Constructors

    /** default constructor */
    public WebPurposeDetailsId()
    {
    }


    /** minimal constructor */
    public WebPurposeDetailsId(String wpdCreatedBy, Date wpdCreatedDt)
    {
        this.wpdCreatedBy = wpdCreatedBy;
        this.wpdCreatedDt = wpdCreatedDt;
    }


    /** full constructor */
    public WebPurposeDetailsId(String wpdChallanId, String wpdMajorHead,
            String wpdSubMajorHead, String wpdMinorHead,
            String wpdSubMinorHead, String wpdDetailHead, String wpdAmount,
            String wpdCreatedBy, Date wpdCreatedDt, String wpdUpdatedBy,
            Date wpdUpdatedDt)
    {
        this.wpdChallanId = wpdChallanId;
        this.wpdMajorHead = wpdMajorHead;
        this.wpdSubMajorHead = wpdSubMajorHead;
        this.wpdMinorHead = wpdMinorHead;
        this.wpdSubMinorHead = wpdSubMinorHead;
        this.wpdDetailHead = wpdDetailHead;
        this.wpdAmount = wpdAmount;
        this.wpdCreatedBy = wpdCreatedBy;
        this.wpdCreatedDt = wpdCreatedDt;
        this.wpdUpdatedBy = wpdUpdatedBy;
        this.wpdUpdatedDt = wpdUpdatedDt;
    }


    // Property accessors
    public String getWpdChallanId()
    {
        return this.wpdChallanId;
    }


    public void setWpdChallanId(String wpdChallanId)
    {
        this.wpdChallanId = wpdChallanId;
    }


    public String getWpdMajorHead()
    {
        return this.wpdMajorHead;
    }


    public void setWpdMajorHead(String wpdMajorHead)
    {
        this.wpdMajorHead = wpdMajorHead;
    }


    public String getWpdSubMajorHead()
    {
        return this.wpdSubMajorHead;
    }


    public void setWpdSubMajorHead(String wpdSubMajorHead)
    {
        this.wpdSubMajorHead = wpdSubMajorHead;
    }


    public String getWpdMinorHead()
    {
        return this.wpdMinorHead;
    }


    public void setWpdMinorHead(String wpdMinorHead)
    {
        this.wpdMinorHead = wpdMinorHead;
    }


    public String getWpdSubMinorHead()
    {
        return this.wpdSubMinorHead;
    }


    public void setWpdSubMinorHead(String wpdSubMinorHead)
    {
        this.wpdSubMinorHead = wpdSubMinorHead;
    }


    public String getWpdDetailHead()
    {
        return this.wpdDetailHead;
    }


    public void setWpdDetailHead(String wpdDetailHead)
    {
        this.wpdDetailHead = wpdDetailHead;
    }


    public String getWpdAmount()
    {
        return this.wpdAmount;
    }


    public void setWpdAmount(String wpdAmount)
    {
        this.wpdAmount = wpdAmount;
    }


    public String getWpdCreatedBy()
    {
        return this.wpdCreatedBy;
    }


    public void setWpdCreatedBy(String wpdCreatedBy)
    {
        this.wpdCreatedBy = wpdCreatedBy;
    }


    public Date getWpdCreatedDt()
    {
        return this.wpdCreatedDt;
    }


    public void setWpdCreatedDt(Date wpdCreatedDt)
    {
        this.wpdCreatedDt = wpdCreatedDt;
    }


    public String getWpdUpdatedBy()
    {
        return this.wpdUpdatedBy;
    }


    public void setWpdUpdatedBy(String wpdUpdatedBy)
    {
        this.wpdUpdatedBy = wpdUpdatedBy;
    }


    public Date getWpdUpdatedDt()
    {
        return this.wpdUpdatedDt;
    }


    public void setWpdUpdatedDt(Date wpdUpdatedDt)
    {
        this.wpdUpdatedDt = wpdUpdatedDt;
    }


    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof WebPurposeDetailsId))
            return false;
        WebPurposeDetailsId castOther = (WebPurposeDetailsId) other;

        return ((this.getWpdChallanId() == castOther.getWpdChallanId()) || (this
                .getWpdChallanId() != null
                && castOther.getWpdChallanId() != null && this
                .getWpdChallanId().equals(castOther.getWpdChallanId())))
                && ((this.getWpdMajorHead() == castOther.getWpdMajorHead()) || (this
                        .getWpdMajorHead() != null
                        && castOther.getWpdMajorHead() != null && this
                        .getWpdMajorHead().equals(castOther.getWpdMajorHead())))
                && ((this.getWpdSubMajorHead() == castOther
                        .getWpdSubMajorHead()) || (this.getWpdSubMajorHead() != null
                        && castOther.getWpdSubMajorHead() != null && this
                        .getWpdSubMajorHead().equals(
                                castOther.getWpdSubMajorHead())))
                && ((this.getWpdMinorHead() == castOther.getWpdMinorHead()) || (this
                        .getWpdMinorHead() != null
                        && castOther.getWpdMinorHead() != null && this
                        .getWpdMinorHead().equals(castOther.getWpdMinorHead())))
                && ((this.getWpdSubMinorHead() == castOther
                        .getWpdSubMinorHead()) || (this.getWpdSubMinorHead() != null
                        && castOther.getWpdSubMinorHead() != null && this
                        .getWpdSubMinorHead().equals(
                                castOther.getWpdSubMinorHead())))
                && ((this.getWpdDetailHead() == castOther.getWpdDetailHead()) || (this
                        .getWpdDetailHead() != null
                        && castOther.getWpdDetailHead() != null && this
                        .getWpdDetailHead()
                        .equals(castOther.getWpdDetailHead())))
                && ((this.getWpdAmount() == castOther.getWpdAmount()) || (this
                        .getWpdAmount() != null
                        && castOther.getWpdAmount() != null && this
                        .getWpdAmount().equals(castOther.getWpdAmount())))
                && ((this.getWpdCreatedBy() == castOther.getWpdCreatedBy()) || (this
                        .getWpdCreatedBy() != null
                        && castOther.getWpdCreatedBy() != null && this
                        .getWpdCreatedBy().equals(castOther.getWpdCreatedBy())))
                && ((this.getWpdCreatedDt() == castOther.getWpdCreatedDt()) || (this
                        .getWpdCreatedDt() != null
                        && castOther.getWpdCreatedDt() != null && this
                        .getWpdCreatedDt().equals(castOther.getWpdCreatedDt())))
                && ((this.getWpdUpdatedBy() == castOther.getWpdUpdatedBy()) || (this
                        .getWpdUpdatedBy() != null
                        && castOther.getWpdUpdatedBy() != null && this
                        .getWpdUpdatedBy().equals(castOther.getWpdUpdatedBy())))
                && ((this.getWpdUpdatedDt() == castOther.getWpdUpdatedDt()) || (this
                        .getWpdUpdatedDt() != null
                        && castOther.getWpdUpdatedDt() != null && this
                        .getWpdUpdatedDt().equals(castOther.getWpdUpdatedDt())));
    }


    public int hashCode()
    {
        int result = 17;

        result = 37
                * result
                + (getWpdChallanId() == null ? 0 : this.getWpdChallanId()
                        .hashCode());
        result = 37
                * result
                + (getWpdMajorHead() == null ? 0 : this.getWpdMajorHead()
                        .hashCode());
        result = 37
                * result
                + (getWpdSubMajorHead() == null ? 0 : this.getWpdSubMajorHead()
                        .hashCode());
        result = 37
                * result
                + (getWpdMinorHead() == null ? 0 : this.getWpdMinorHead()
                        .hashCode());
        result = 37
                * result
                + (getWpdSubMinorHead() == null ? 0 : this.getWpdSubMinorHead()
                        .hashCode());
        result = 37
                * result
                + (getWpdDetailHead() == null ? 0 : this.getWpdDetailHead()
                        .hashCode());
        result = 37 * result
                + (getWpdAmount() == null ? 0 : this.getWpdAmount().hashCode());
        result = 37
                * result
                + (getWpdCreatedBy() == null ? 0 : this.getWpdCreatedBy()
                        .hashCode());
        result = 37
                * result
                + (getWpdCreatedDt() == null ? 0 : this.getWpdCreatedDt()
                        .hashCode());
        result = 37
                * result
                + (getWpdUpdatedBy() == null ? 0 : this.getWpdUpdatedBy()
                        .hashCode());
        result = 37
                * result
                + (getWpdUpdatedDt() == null ? 0 : this.getWpdUpdatedDt()
                        .hashCode());
        return result;
    }

}
