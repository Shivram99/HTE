package com.tcs.sgv.eis.valueobject;

// Generated Jul 4, 2007 4:12:12 PM by Hibernate Tools 3.1.0.beta5

import java.util.Date;

import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


/**
 * HrEisScaleMst generated by hbm2java
 */
public class HrEisScaleMst implements java.io.Serializable {

	// Fields    
	
	
	private CmnLanguageMst cmnLanguageMst;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;
	
	private HrPayCommissionMst hrPayCommissionMst;
	
	private CmnLookupMst payBandId;
	



	private long scaleId;

	private String scaleName;

	private String scaleDesc;

	private long scaleStartAmt;

	private long scaleEndAmt;

	private long scaleIncrAmt;
	
	private Date createdDate;
	
	private Date scaleEffFromDt;
	
	private Date scaleEffToDt;
	
	private Date updatedDate;
	
	private Long elementCode;
	
	//private long monthsForIncrement;
	
	//Added by Mrugesh for Bigger Scale Value
	private long scaleHigherIncrAmt;
	
	private long scaleHigherEndAmt;
	//Ended by mrugesh
	private Integer trnCounter;
	
	private String currencyStartAmount;
	
	private String currencyEndAmount;
	
	private String currencyHigherEndAmount;
	
	private long scaleGradePay;
	
	private long scaleSecondHigherIncrAmt;
	
	private long scaleSecondHigherEndAmt;
	
	private long scaleThirdHigherIncrAmt;
	
	private long scaleThirdHigherEndAmt;
	
	private CmnLookupMst payScaleType; //Added by Muneendra for  payScaleType
	
	private Date incrementDate; //Added by Muneendra for  incrementDate
	
	private Integer migratedScale;

	/*private long langId;

	private long locId;

	private long dbId;

	private Long elementCode;

	private long createdBy;

	private Date createdDate;

	private long createdByPost;

	private Long updatedBy;

	private Date updatedDate;

	private Long updatedByPost;
	
	private Date scaleEffFromDt;
	
	private Date scaleEffToDt;*/

	// Constructors

	
	/** default constructor */
	public HrEisScaleMst() {
	}

	/** minimal constructor */
	public HrEisScaleMst(long scaleId, String scaleName, String scaleDesc,
			long scaleStartAmt, long scaleEndAmt, long scaleIncrAmt,
			CmnLanguageMst cmnLanguageMst, CmnLocationMst cmnLocationMst, CmnDatabaseMst cmnDatabaseMst, OrgUserMst orgUserMstByCreatedBy,
			Date createdDate, OrgPostMst orgPostMstByCreatedByPost,long scaleHigherIncrAmt,long scaleHigherEndAmt,CmnLookupMst payScaleType) {
		this.scaleId = scaleId;
		this.scaleName = scaleName;
		this.scaleDesc = scaleDesc;
		this.scaleStartAmt = scaleStartAmt;
		this.scaleEndAmt = scaleEndAmt;
		this.scaleIncrAmt = scaleIncrAmt;
		this.cmnLanguageMst = cmnLanguageMst; 
		this.cmnLocationMst = cmnLocationMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.scaleHigherIncrAmt = scaleHigherIncrAmt;
		this.scaleHigherEndAmt = scaleHigherEndAmt;
		this.payScaleType=payScaleType;
		
	}

	/** full constructor */
	public HrEisScaleMst(long scaleId, String scaleName, String scaleDesc,
			long scaleStartAmt, long scaleEndAmt, long scaleIncrAmt,
			CmnLanguageMst cmnLanguageMst, CmnLocationMst cmnLocationMst, CmnDatabaseMst cmnDatabaseMst, Long elementCode,
			OrgUserMst orgUserMstByCreatedBy, Date createdDate, OrgPostMst orgPostMstByCreatedByPost,
			OrgUserMst orgUserMstByUpdatedBy, Date updatedDate, OrgPostMst orgPostMstByUpdatedByPost, 
			Date scaleEffFromDt, Date scaleEffToDt,Integer trnCounter,long scaleGradePay,CmnLookupMst payScaleType) {
		this.scaleId = scaleId;
		this.scaleName = scaleName;
		this.scaleDesc = scaleDesc;
		this.scaleStartAmt = scaleStartAmt;
		this.scaleEndAmt = scaleEndAmt;
		this.scaleIncrAmt = scaleIncrAmt;
		this.cmnLanguageMst = cmnLanguageMst;
		this.cmnLocationMst = cmnLocationMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.elementCode = elementCode;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.updatedDate = updatedDate;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.scaleEffFromDt = scaleEffFromDt;
		this.scaleEffToDt = scaleEffToDt;
		this.trnCounter = trnCounter;
		this.scaleGradePay=scaleGradePay;
		this.payScaleType=payScaleType;
	}
	public long getScaleGradePay() {
		return scaleGradePay;
	}

	public void setScaleGradePay(long scaleGradePay) {
		this.scaleGradePay = scaleGradePay;
	}

	// Property accessors
	public long getScaleId() {
		return this.scaleId;
	}

	public void setScaleId(long scaleId) {
		this.scaleId = scaleId;
	}

	public String getScaleName() {
		return this.scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public String getScaleDesc() {
		return this.scaleDesc;
	}

	public void setScaleDesc(String scaleDesc) {
		this.scaleDesc = scaleDesc;
	}

	public long getScaleStartAmt() {
		return this.scaleStartAmt;
	}

	public void setScaleStartAmt(long scaleStartAmt) {
		this.scaleStartAmt = scaleStartAmt;
	}

	public long getScaleEndAmt() {
		return this.scaleEndAmt;
	}

	public void setScaleEndAmt(long scaleEndAmt) {
		this.scaleEndAmt = scaleEndAmt;
	}

	public long getScaleIncrAmt() {
		return this.scaleIncrAmt;
	}

	public void setScaleIncrAmt(long scaleIncrAmt) {
		this.scaleIncrAmt = scaleIncrAmt;
	}

	/*public long getLangId() {
		return this.langId;
	}

	public void setLangId(long langId) {
		this.langId = langId;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}*/

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/*public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}*/

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public Long getElementCode() {
		return this.elementCode;
	}

	public void setElementCode(Long elementCode) {
		this.elementCode = elementCode;
	}
	/*public Long getUpdatedByPost() {
		return this.updatedByPost;
	}

	public void setUpdatedByPost(Long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}*/

	public Date getScaleEffFromDt() {
		return scaleEffFromDt;
	}

	public void setScaleEffFromDt(Date scaleEffFromDt) {
		this.scaleEffFromDt = scaleEffFromDt;
	}

	public Date getScaleEffToDt() {
		return scaleEffToDt;
	}

	public void setScaleEffToDt(Date scaleEffToDt) {
		this.scaleEffToDt = scaleEffToDt;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLanguageMst getCmnLanguageMst() {
		return cmnLanguageMst;
	}

	public void setCmnLanguageMst(CmnLanguageMst cmnLanguageMst) {
		this.cmnLanguageMst = cmnLanguageMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	/*public long getMonthsForIncrement() {
		return monthsForIncrement;
	}

	public void setMonthsForIncrement(long monthsForIncrement) {
		this.monthsForIncrement = monthsForIncrement;
	}*/

	public long getScaleHigherEndAmt() {
		return scaleHigherEndAmt;
	}

	public void setScaleHigherEndAmt(long scaleHigherEndAmt) {
		this.scaleHigherEndAmt = scaleHigherEndAmt;
	}

	public long getScaleHigherIncrAmt() {
		return scaleHigherIncrAmt;
	}

	public void setScaleHigherIncrAmt(long scaleHigherIncrAmt) {
		this.scaleHigherIncrAmt = scaleHigherIncrAmt;
	}

	public Integer getTrnCounter() {
		return trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}
	
	public String getCurrencyStartAmount() {
		return this.currencyStartAmount;
	}

	public void setCurrencyStartAmount(String currencyStartAmount) {
		this.currencyStartAmount = currencyStartAmount;
	}

	public String getCurrencyEndAmount() {
		return currencyEndAmount;
	}

	public void setCurrencyEndAmount(String currencyEndAmount) {
		this.currencyEndAmount = currencyEndAmount;
	}

	public String getCurrencyHigherEndAmount() {
		return currencyHigherEndAmount;
	}

	public void setCurrencyHigherEndAmount(String currencyHigherEndAmount) {
		this.currencyHigherEndAmount = currencyHigherEndAmount;
	}
	
	public HrPayCommissionMst getHrPayCommissionMst() {
		return hrPayCommissionMst;
	}

	public void setHrPayCommissionMst(HrPayCommissionMst hrPayCommissionMst) {
		this.hrPayCommissionMst = hrPayCommissionMst;
	}

	public CmnLookupMst getPayBandId() {
		return payBandId;
	}

	public void setPayBandId(CmnLookupMst payBandId) {
		this.payBandId = payBandId;
	}

	/**
	 * @return the scaleSecondHigherIncrAmt
	 */
	public long getScaleSecondHigherIncrAmt() {
		return scaleSecondHigherIncrAmt;
	}

	/**
	 * @param scaleSecondHigherIncrAmt the scaleSecondHigherIncrAmt to set
	 */
	public void setScaleSecondHigherIncrAmt(long scaleSecondHigherIncrAmt) {
		this.scaleSecondHigherIncrAmt = scaleSecondHigherIncrAmt;
	}

	/**
	 * @return the scaleSecondHigherEndAmt
	 */
	public long getScaleSecondHigherEndAmt() {
		return scaleSecondHigherEndAmt;
	}

	/**
	 * @param scaleSecondHigherEndAmt the scaleSecondHigherEndAmt to set
	 */
	public void setScaleSecondHigherEndAmt(long scaleSecondHigherEndAmt) {
		this.scaleSecondHigherEndAmt = scaleSecondHigherEndAmt;
	}

	/**
	 * @return the scaleThirdHigherIncrAmt
	 */
	public long getScaleThirdHigherIncrAmt() {
		return scaleThirdHigherIncrAmt;
	}

	/**
	 * @param scaleThirdHigherIncrAmt the scaleThirdHigherIncrAmt to set
	 */
	public void setScaleThirdHigherIncrAmt(long scaleThirdHigherIncrAmt) {
		this.scaleThirdHigherIncrAmt = scaleThirdHigherIncrAmt;
	}

	/**
	 * @return the scaleThirdHigherEndAmt
	 */
	public long getScaleThirdHigherEndAmt() {
		return scaleThirdHigherEndAmt;
	}

	/**
	 * @param scaleThirdHigherEndAmt the scaleThirdHigherEndAmt to set
	 */
	public void setScaleThirdHigherEndAmt(long scaleThirdHigherEndAmt) {
		this.scaleThirdHigherEndAmt = scaleThirdHigherEndAmt;
	}
	
	public CmnLookupMst getPayScaleType() {
		return payScaleType;
	}

	public void setPayScaleType(CmnLookupMst payScaleType) {
		this.payScaleType = payScaleType;
	}

	public Date getIncrementDate() {
		return incrementDate;
	}
	
	public void setIncrementDate(Date incrementDate) {
		this.incrementDate = incrementDate;
	}

	/**
	 * @return the migratedScale
	 */
	public Integer getMigratedScale() {
		return migratedScale;
	}

	/**
	 * @param migratedScale the migratedScale to set
	 */
	public void setMigratedScale(Integer migratedScale) {
		this.migratedScale = migratedScale;
	}
	
}//end class
