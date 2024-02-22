package com.tcs.sgv.eis.valueobject;

import java.io.Serializable;
import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrPayDDOSchemeMpgVO implements Serializable
{

	private long Id;
	
	private OrgSchemeMstVO orgSchemeMstVO;

	private OrgDdoMst orgDdoMst;
	
	private OrgUserMst orgUserMstByCreatedBy;
	
	private Date createdDate;
	
	private OrgPostMst orgPostMstByCreatedByPost;
	
	private OrgUserMst orgUserMstByUpdatedBy;
	
	private Date updatedDate;
	
	private OrgPostMst orgPostMstByUpdatedByPost;
	
    private CmnLanguageMst cmnLanguageMst;
    
    private CmnLocationMst cmnLocationMst;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public OrgSchemeMstVO getOrgSchemeMstVO() {
		return orgSchemeMstVO;
	}

	public void setOrgSchemeMstVO(OrgSchemeMstVO orgSchemeMstVO) {
		this.orgSchemeMstVO = orgSchemeMstVO;
	}

	public OrgDdoMst getOrgDdoMst() {
		return orgDdoMst;
	}

	public void setOrgDdoMst(OrgDdoMst orgDdoMst) {
		this.orgDdoMst = orgDdoMst;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
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

	
	
	
	
}
