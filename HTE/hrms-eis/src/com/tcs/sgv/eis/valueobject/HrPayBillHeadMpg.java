package com.tcs.sgv.eis.valueobject;

// default package
// Generated Oct 9, 2007 12:44:58 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrPayOrderHeadMpg generated by hbm2java
 */
public class HrPayBillHeadMpg implements java.io.Serializable {

	// Fields    

	private long billHeadId;

	private long billId;

	private Long subheadId;
	
	private Long finYearId;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private Date updatedDate;
		
	//private long loc_id;
	CmnLocationMst cmnLocationMst;
	
	String dsgn_Id;
	
	String class_Id;
	
	// Added By Urvin Shah.
	public CmnLookupMst postType;
	
	private String headChargable;
	
	OrgSchemeMstVO orgSchemeMst;
	//End.
//	 Constructors

	public String getDsgn_Id() {
		return dsgn_Id;
	}

	public void setDsgn_Id(String dsgn_Id) {
		this.dsgn_Id = dsgn_Id;
	}

	/** default constructor */
	public HrPayBillHeadMpg() {
	}

	/** minimal constructor */
	public HrPayBillHeadMpg(long billHeadId) {
		this.billHeadId = billHeadId;
	}

	/** full constructor */
	public HrPayBillHeadMpg(long billHeadId, long billId,
			Long subheadId,OrgUserMst orgUserMstByCreatedBy , OrgPostMst orgPostMstByCreatedByPost, Date createdDate,CmnLookupMst postType,
			OrgUserMst orgUserMstByUpdatedBy, OrgPostMst orgPostMstByUpdatedByPost , Date updatedDate , long loc_id , Long finYearId , String dsgn_Id , String class_Id) {
		this.billHeadId = billHeadId;
		this.billId = billId;
		this.subheadId = subheadId;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.createdDate = createdDate;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.updatedDate = updatedDate;
		//this.loc_id = loc_id;
		this.finYearId = finYearId;
		this.dsgn_Id = dsgn_Id;
		// Added By Urvin Shah.
		this.postType = postType;
	}
	
	

	public long getBillHeadId() {
		return billHeadId;
	}

	public void setBillHeadId(long billHeadId) {
		this.billHeadId = billHeadId;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/*public long getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(long loc_id) {
		this.loc_id = loc_id;
	}*/

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

	public Long getSubheadId() {
		return subheadId;
	}

	public void setSubheadId(Long subheadId) {
		this.subheadId = subheadId;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Long finYearId) {
		this.finYearId = finYearId;
	}

	public String getClass_Id() {
		return class_Id;
	}

	public void setClass_Id(String class_Id) {
		this.class_Id = class_Id;
	}

	// Added By Urvin Shah.
	
	public CmnLookupMst getPostType() {
		return postType;
	}

	public void setPostType(CmnLookupMst postType) {
		this.postType = postType;
	}
	// end.

	public String getHeadChargable() {
		return headChargable;
	}

	public void setHeadChargable(String headChargable) {
		this.headChargable = headChargable;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgSchemeMstVO getOrgSchemeMst() {
		return orgSchemeMst;
	}

	public void setOrgSchemeMst(OrgSchemeMstVO orgSchemeMst) {
		this.orgSchemeMst = orgSchemeMst;
	}
	
}