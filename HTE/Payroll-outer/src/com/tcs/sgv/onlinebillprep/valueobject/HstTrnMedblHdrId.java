package com.tcs.sgv.onlinebillprep.valueobject;

// Generated Sep 26, 2007 5:51:26 PM by Hibernate Tools 3.2.0.beta8


/**
 * HstTrnMedblHdrId generated by hbm2java
 */
public class HstTrnMedblHdrId implements java.io.Serializable
{

    // Fields    

    private Long trnMedblHdrId;

    private Integer trnCounter;


    // Constructors

    /** default constructor */
    public HstTrnMedblHdrId()
    {
    }


    /** full constructor */
    public HstTrnMedblHdrId(Long trnMedblHdrId, Integer trnCounter)
    {
        this.trnMedblHdrId = trnMedblHdrId;
        this.trnCounter = trnCounter;
    }


    // Property accessors
    public Long getTrnMedblHdrId()
    {
        return this.trnMedblHdrId;
    }


    public void setTrnMedblHdrId(Long trnMedblHdrId)
    {
        this.trnMedblHdrId = trnMedblHdrId;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }


    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof HstTrnMedblHdrId))
            return false;
        HstTrnMedblHdrId castOther = (HstTrnMedblHdrId) other;

        return ((this.getTrnMedblHdrId() == castOther.getTrnMedblHdrId()) || (this.getTrnMedblHdrId() != null
                && castOther.getTrnMedblHdrId() != null && this.getTrnMedblHdrId().equals(castOther.getTrnMedblHdrId())))
                && ((this.getTrnCounter() == castOther.getTrnCounter()) || (this.getTrnCounter() != null
                        && castOther.getTrnCounter() != null && this.getTrnCounter().equals(castOther.getTrnCounter())));
    }


    public int hashCode()
    {
        int result = 17;

        result = 37 * result + (getTrnMedblHdrId() == null ? 0 : this.getTrnMedblHdrId().hashCode());
        result = 37 * result + (getTrnCounter() == null ? 0 : this.getTrnCounter().hashCode());
        return result;
    }

}
