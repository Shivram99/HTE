package com.tcs.sgv.eis.valueobject;
// Generated Mar 14, 2008 10:59:36 AM by Hibernate Tools 3.2.0.beta8

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import java.util.Date;

/**
 * HrGratuityleaveencashTxn generated by hbm2java
 */
public class HrPayLeaveEncashDtl implements java.io.Serializable {

	// Fields    

	private long encashId;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgUserMst orgUserMstByUserId;

	private double elAmount;

	private Integer status;
	
	private double noOfEl;

	private Date createdDate;

	private Date updatedDate;

	public long getEncashId() {
		return encashId;
	}

	public void setEncashId(long encashId) {
		this.encashId = encashId;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public OrgUserMst getOrgUserMstByUserId() {
		return orgUserMstByUserId;
	}

	public void setOrgUserMstByUserId(OrgUserMst orgUserMstByUserId) {
		this.orgUserMstByUserId = orgUserMstByUserId;
	}

	public double getElAmount() {
		return elAmount;
	}

	public void setElAmount(double elAmount) {
		this.elAmount = elAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public double getNoOfEl() {
		return noOfEl;
	}

	public void setNoOfEl(double noOfEl) {
		this.noOfEl = noOfEl;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	

	
}
