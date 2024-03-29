// default package
// Generated Aug 25, 2007 3:44:05 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrLoanEmpIntRecoverHst generated by hbm2java
 */
public class HrLoanEmpIntRecoverHst implements java.io.Serializable {

	// Fields    

	private HrLoanEmpIntRecoverHstId id;

	//private long empLoanId;

	private long totalRecoveredInt;

	private long totalRecoveredIntInst;

	/*private long dbId;

	private long locId;

	private long createdBy;

	private long createdByPost;*/

	private Date createdDate;

	/*private long updatedBy;

	private long updatedByPost;*/

	private Date updatedDate;
	
	private HrLoanEmpDtls hrLoanEmpDtls;
	
	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;
	

	// Constructors

	/** default constructor */
	public HrLoanEmpIntRecoverHst() {
	}

	/** minimal constructor */
	public HrLoanEmpIntRecoverHst(HrLoanEmpIntRecoverHstId id,
			long totalRecoveredInt,long totalRecoveredIntInst,Date createdDate,
			HrLoanEmpDtls hrLoanEmpDtls,CmnDatabaseMst cmnDatabaseMst,CmnLocationMst cmnLocationMst,
			OrgUserMst orgUserMstByCreatedBy,OrgPostMst orgPostMstByCreatedByPost) {
		this.id = id;
		//this.empLoanId = empLoanId;
		this.totalRecoveredInt = totalRecoveredInt;
		this.totalRecoveredIntInst = totalRecoveredIntInst;
		/*this.dbId = dbId;
		this.locId = locId;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;*/
		this.createdDate = createdDate;
		this.hrLoanEmpDtls = hrLoanEmpDtls;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		
	}

	/** full constructor */
	public HrLoanEmpIntRecoverHst(HrLoanEmpIntRecoverHstId id,
			long totalRecoveredInt,long totalRecoveredIntInst,
			Date createdDate,Date updatedDate,
			HrLoanEmpDtls hrLoanEmpDtls,CmnDatabaseMst cmnDatabaseMst,CmnLocationMst 
			cmnLocationMst, OrgUserMst orgUserMstByCreatedBy, 
			OrgPostMst orgPostMstByCreatedByPost,
			OrgUserMst orgUserMstByUpdatedBy,  OrgPostMst orgPostMstByUpdatedByPost) {
		this.id = id;
		//this.empLoanId = empLoanId;
		this.hrLoanEmpDtls = hrLoanEmpDtls;
		this.totalRecoveredInt = totalRecoveredInt;
		this.totalRecoveredIntInst = totalRecoveredIntInst;
		/*this.dbId = dbId;
		this.locId = locId;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;*/
		this.updatedDate = updatedDate;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	// Property accessors
	public HrLoanEmpIntRecoverHstId getId() {
		return this.id;
	}

	public void setId(HrLoanEmpIntRecoverHstId id) {
		this.id = id;
	}

	/*public long getEmpLoanId() {
		return this.empLoanId;
	}

	public void setEmpLoanId(long empLoanId) {
		this.empLoanId = empLoanId;
	}*/

	public long getTotalRecoveredInt() {
		return this.totalRecoveredInt;
	}

	public void setTotalRecoveredInt(long totalRecoveredInt) {
		this.totalRecoveredInt = totalRecoveredInt;
	}

	public long getTotalRecoveredIntInst() {
		return this.totalRecoveredIntInst;
	}

	public void setTotalRecoveredIntInst(long totalRecoveredIntInst) {
		this.totalRecoveredIntInst = totalRecoveredIntInst;
	}

	/*public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}*/

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/*public long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public long getUpdatedByPost() {
		return this.updatedByPost;
	}

	public void setUpdatedByPost(long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}*/

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public HrLoanEmpDtls getHrLoanEmpDtls() {
		return hrLoanEmpDtls;
	}

	public void setHrLoanEmpDtls(HrLoanEmpDtls hrLoanEmpDtls) {
		this.hrLoanEmpDtls = hrLoanEmpDtls;
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

}
