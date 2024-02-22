package com.tcs.sgv.common.valueobject;

// Generated Jun 9, 2007 10:17:29 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * SgvaBudcssMstId generated by hbm2java
 */
public class SgvaBudcssMstId implements java.io.Serializable
{

    // Fields    

    private String budbpnCode;

    private String buddemandCode;

    private String budmjrhdCode;

    private String budsubmjrhdCode;

    private String budminhdCode;

    private String budsubhdCode;

    private String planNonplan;

    private BigDecimal cssAmount;

    private Long finYrId;

    private String langId;

    private String locId;

    private Date crtDt;

    private String crtUsr;

    private Date lstUpdDt;

    private String lstUpdUsr;


    // Constructors

    /** default constructor */
    public SgvaBudcssMstId()
    {
    }


    /** full constructor */
    public SgvaBudcssMstId(String budbpnCode, String buddemandCode,
            String budmjrhdCode, String budsubmjrhdCode, String budminhdCode,
            String budsubhdCode, String planNonplan, BigDecimal cssAmount,
            Long finYrId, String langId, String locId, Date crtDt,
            String crtUsr, Date lstUpdDt, String lstUpdUsr)
    {
        this.budbpnCode = budbpnCode;
        this.buddemandCode = buddemandCode;
        this.budmjrhdCode = budmjrhdCode;
        this.budsubmjrhdCode = budsubmjrhdCode;
        this.budminhdCode = budminhdCode;
        this.budsubhdCode = budsubhdCode;
        this.planNonplan = planNonplan;
        this.cssAmount = cssAmount;
        this.finYrId = finYrId;
        this.langId = langId;
        this.locId = locId;
        this.crtDt = crtDt;
        this.crtUsr = crtUsr;
        this.lstUpdDt = lstUpdDt;
        this.lstUpdUsr = lstUpdUsr;
    }


    // Property accessors
    public String getBudbpnCode()
    {
        return this.budbpnCode;
    }


    public void setBudbpnCode(String budbpnCode)
    {
        this.budbpnCode = budbpnCode;
    }


    public String getBuddemandCode()
    {
        return this.buddemandCode;
    }


    public void setBuddemandCode(String buddemandCode)
    {
        this.buddemandCode = buddemandCode;
    }


    public String getBudmjrhdCode()
    {
        return this.budmjrhdCode;
    }


    public void setBudmjrhdCode(String budmjrhdCode)
    {
        this.budmjrhdCode = budmjrhdCode;
    }


    public String getBudsubmjrhdCode()
    {
        return this.budsubmjrhdCode;
    }


    public void setBudsubmjrhdCode(String budsubmjrhdCode)
    {
        this.budsubmjrhdCode = budsubmjrhdCode;
    }


    public String getBudminhdCode()
    {
        return this.budminhdCode;
    }


    public void setBudminhdCode(String budminhdCode)
    {
        this.budminhdCode = budminhdCode;
    }


    public String getBudsubhdCode()
    {
        return this.budsubhdCode;
    }


    public void setBudsubhdCode(String budsubhdCode)
    {
        this.budsubhdCode = budsubhdCode;
    }


    public String getPlanNonplan()
    {
        return this.planNonplan;
    }


    public void setPlanNonplan(String planNonplan)
    {
        this.planNonplan = planNonplan;
    }


    public BigDecimal getCssAmount()
    {
        return this.cssAmount;
    }


    public void setCssAmount(BigDecimal cssAmount)
    {
        this.cssAmount = cssAmount;
    }


    public Long getFinYrId()
    {
        return this.finYrId;
    }


    public void setFinYrId(Long finYrId)
    {
        this.finYrId = finYrId;
    }


    public String getLangId()
    {
        return this.langId;
    }


    public void setLangId(String langId)
    {
        this.langId = langId;
    }


    public String getLocId()
    {
        return this.locId;
    }


    public void setLocId(String locId)
    {
        this.locId = locId;
    }


    public Date getCrtDt()
    {
        return this.crtDt;
    }


    public void setCrtDt(Date crtDt)
    {
        this.crtDt = crtDt;
    }


    public String getCrtUsr()
    {
        return this.crtUsr;
    }


    public void setCrtUsr(String crtUsr)
    {
        this.crtUsr = crtUsr;
    }


    public Date getLstUpdDt()
    {
        return this.lstUpdDt;
    }


    public void setLstUpdDt(Date lstUpdDt)
    {
        this.lstUpdDt = lstUpdDt;
    }


    public String getLstUpdUsr()
    {
        return this.lstUpdUsr;
    }


    public void setLstUpdUsr(String lstUpdUsr)
    {
        this.lstUpdUsr = lstUpdUsr;
    }


    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SgvaBudcssMstId))
            return false;
        SgvaBudcssMstId castOther = (SgvaBudcssMstId) other;

        return ((this.getBudbpnCode() == castOther.getBudbpnCode()) || (this
                .getBudbpnCode() != null
                && castOther.getBudbpnCode() != null && this.getBudbpnCode()
                .equals(castOther.getBudbpnCode())))
                && ((this.getBuddemandCode() == castOther.getBuddemandCode()) || (this
                        .getBuddemandCode() != null
                        && castOther.getBuddemandCode() != null && this
                        .getBuddemandCode()
                        .equals(castOther.getBuddemandCode())))
                && ((this.getBudmjrhdCode() == castOther.getBudmjrhdCode()) || (this
                        .getBudmjrhdCode() != null
                        && castOther.getBudmjrhdCode() != null && this
                        .getBudmjrhdCode().equals(castOther.getBudmjrhdCode())))
                && ((this.getBudsubmjrhdCode() == castOther
                        .getBudsubmjrhdCode()) || (this.getBudsubmjrhdCode() != null
                        && castOther.getBudsubmjrhdCode() != null && this
                        .getBudsubmjrhdCode().equals(
                                castOther.getBudsubmjrhdCode())))
                && ((this.getBudminhdCode() == castOther.getBudminhdCode()) || (this
                        .getBudminhdCode() != null
                        && castOther.getBudminhdCode() != null && this
                        .getBudminhdCode().equals(castOther.getBudminhdCode())))
                && ((this.getBudsubhdCode() == castOther.getBudsubhdCode()) || (this
                        .getBudsubhdCode() != null
                        && castOther.getBudsubhdCode() != null && this
                        .getBudsubhdCode().equals(castOther.getBudsubhdCode())))
                && ((this.getPlanNonplan() == castOther.getPlanNonplan()) || (this
                        .getPlanNonplan() != null
                        && castOther.getPlanNonplan() != null && this
                        .getPlanNonplan().equals(castOther.getPlanNonplan())))
                && ((this.getCssAmount() == castOther.getCssAmount()) || (this
                        .getCssAmount() != null
                        && castOther.getCssAmount() != null && this
                        .getCssAmount().equals(castOther.getCssAmount())))
                && ((this.getFinYrId() == castOther.getFinYrId()) || (this
                        .getFinYrId() != null
                        && castOther.getFinYrId() != null && this.getFinYrId()
                        .equals(castOther.getFinYrId())))
                && ((this.getLangId() == castOther.getLangId()) || (this
                        .getLangId() != null
                        && castOther.getLangId() != null && this.getLangId()
                        .equals(castOther.getLangId())))
                && ((this.getLocId() == castOther.getLocId()) || (this
                        .getLocId() != null
                        && castOther.getLocId() != null && this.getLocId()
                        .equals(castOther.getLocId())))
                && ((this.getCrtDt() == castOther.getCrtDt()) || (this
                        .getCrtDt() != null
                        && castOther.getCrtDt() != null && this.getCrtDt()
                        .equals(castOther.getCrtDt())))
                && ((this.getCrtUsr() == castOther.getCrtUsr()) || (this
                        .getCrtUsr() != null
                        && castOther.getCrtUsr() != null && this.getCrtUsr()
                        .equals(castOther.getCrtUsr())))
                && ((this.getLstUpdDt() == castOther.getLstUpdDt()) || (this
                        .getLstUpdDt() != null
                        && castOther.getLstUpdDt() != null && this
                        .getLstUpdDt().equals(castOther.getLstUpdDt())))
                && ((this.getLstUpdUsr() == castOther.getLstUpdUsr()) || (this
                        .getLstUpdUsr() != null
                        && castOther.getLstUpdUsr() != null && this
                        .getLstUpdUsr().equals(castOther.getLstUpdUsr())));
    }


    public int hashCode()
    {
        int result = 17;

        result = 37
                * result
                + (getBudbpnCode() == null ? 0 : this.getBudbpnCode()
                        .hashCode());
        result = 37
                * result
                + (getBuddemandCode() == null ? 0 : this.getBuddemandCode()
                        .hashCode());
        result = 37
                * result
                + (getBudmjrhdCode() == null ? 0 : this.getBudmjrhdCode()
                        .hashCode());
        result = 37
                * result
                + (getBudsubmjrhdCode() == null ? 0 : this.getBudsubmjrhdCode()
                        .hashCode());
        result = 37
                * result
                + (getBudminhdCode() == null ? 0 : this.getBudminhdCode()
                        .hashCode());
        result = 37
                * result
                + (getBudsubhdCode() == null ? 0 : this.getBudsubhdCode()
                        .hashCode());
        result = 37
                * result
                + (getPlanNonplan() == null ? 0 : this.getPlanNonplan()
                        .hashCode());
        result = 37 * result
                + (getCssAmount() == null ? 0 : this.getCssAmount().hashCode());
        result = 37 * result
                + (getFinYrId() == null ? 0 : this.getFinYrId().hashCode());
        result = 37 * result
                + (getLangId() == null ? 0 : this.getLangId().hashCode());
        result = 37 * result
                + (getLocId() == null ? 0 : this.getLocId().hashCode());
        result = 37 * result
                + (getCrtDt() == null ? 0 : this.getCrtDt().hashCode());
        result = 37 * result
                + (getCrtUsr() == null ? 0 : this.getCrtUsr().hashCode());
        result = 37 * result
                + (getLstUpdDt() == null ? 0 : this.getLstUpdDt().hashCode());
        result = 37 * result
                + (getLstUpdUsr() == null ? 0 : this.getLstUpdUsr().hashCode());
        return result;
    }

}
