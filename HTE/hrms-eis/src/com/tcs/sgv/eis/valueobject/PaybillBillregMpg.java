// default package
// Generated Nov 22, 2007 5:59:07 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * PaybillBillregMpg generated by hbm2java
 */
public class PaybillBillregMpg implements java.io.Serializable {

	// Fields    

	private long id;

	private long hrPayPaybill;

	private TrnBillRegister trnBillRegister;

	private CmnDatabaseMst cmnDatabaseMst;
	private OrgPostMst orgPostMstByUpdatedByPost;
	private OrgPostMst orgPostMstByCreatedByPost;
	private CmnLocationMst cmnLocationMst;
	private OrgUserMst orgUserMstByUpdatedBy;
	private OrgUserMst orgUserMstByCreatedBy;	
	private Date createdDate;
	private Date updatedDate;
	private CmnLookupMst billTypeId;
	//private HrPayArrearPaybill arrearBillId;
	// Constructors

	/*public HrPayArrearPaybill getArrearBillId() {
		return arrearBillId;
	}

	public void setArrearBillId(HrPayArrearPaybill arrearBillId) {
		this.arrearBillId = arrearBillId;
	}*/

	/** default constructor */
	public PaybillBillregMpg() {
	}

	/** minimal constructor */
	public PaybillBillregMpg(long id) {
		this.id = id;
	}

	/** full constructor */
	public PaybillBillregMpg(long id, long paybillId,
			long trnBillId, long locId, long dbId,
			long createdBy, Date createdDate, long updatedBy,
			Date updatedDate, long createdByPost, long updatedByPost) {
		this.id = id;				
		this.createdDate = createdDate;		
		this.updatedDate = updatedDate;		
	}

	// Property accessors
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public long getHrPayPaybill() {
		return hrPayPaybill;
	}

	public void setHrPayPaybill(long hrPayPaybill) {
		this.hrPayPaybill = hrPayPaybill;
	}

	public TrnBillRegister getTrnBillRegister() {
		return trnBillRegister;
	}

	public void setTrnBillRegister(TrnBillRegister trnBillRegister) {
		this.trnBillRegister = trnBillRegister;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
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

	public CmnLookupMst getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(CmnLookupMst billTypeId) {
		this.billTypeId = billTypeId;
	}

}
