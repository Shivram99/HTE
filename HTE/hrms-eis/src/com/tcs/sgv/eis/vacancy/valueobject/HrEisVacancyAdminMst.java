package com.tcs.sgv.eis.vacancy.valueobject;

// default package
// Generated Feb 18, 2008 6:25:28 AM by Hibernate Tools 3.2.0.beta8


import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


/**
 * HrGradationMst generated by hbm2java
 */
public class HrEisVacancyAdminMst implements java.io.Serializable {

	// Fields    

	private long srno;
	
	private CmnDatabaseMst cmnDatabaseMst;
	
	private CmnLocationMst cmnLocationMst;

	private OrgPostMst orgPostMstByUpdatedByPost;
	
	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgUserMst orgUserMstByUpdatedBy;
	
	private Date createdDate;

	private Date updatedDate;
	
	private String locCode;
	
	private String dsgnCode;
	
	private String branchCode;

	private Long sanctionedStrength;
	
	public HrEisVacancyAdminMst(){
		
	}

	public HrEisVacancyAdminMst(long srno, CmnDatabaseMst cmnDatabaseMst, CmnLocationMst cmnLocationMst, OrgPostMst orgPostMstByUpdatedByPost, OrgPostMst orgPostMstByCreatedByPost, OrgUserMst orgUserMstByCreatedBy, OrgUserMst orgUserMstByUpdatedBy, Date createdDate, Date updatedDate, String locCode, String dsgnCode, String branchCode, Long sanctionedStrength) {
		super();
		this.srno = srno;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.locCode = locCode;
		this.dsgnCode = dsgnCode;
		this.branchCode = branchCode;
		this.sanctionedStrength = sanctionedStrength;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public void setCmnLocationMst(CmnLocationMst cmnLocationMstId) {
		this.cmnLocationMst = cmnLocationMstId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDsgnCode() {
		return dsgnCode;
	}

	public void setDsgnCode(String dsgnCode) {
		this.dsgnCode = dsgnCode;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
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

	public Long getSanctionedStrength() {
		return sanctionedStrength;
	}

	public void setSanctionedStrength(Long sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}

	public long getSrno() {
		return srno;
	}

	public void setSrno(long srno) {
		this.srno = srno;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
