package com.tcs.sgv.eis.valueobject;

// default package
// Generated Feb 8, 2008 7:42:29 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrEnqEmpDtl generated by hbm2java
 */
public class HrPayCompGrpMst implements java.io.Serializable {

	// Fields    

	private long compoGrpId;

	private long month;
	
	private long year;
	
	private CmnLocationMst cmnLocationMst;
	
	private long isactive;
	
	private Date wefDate;

	private String remarks;
	
	private OrgPostMst createdBy;
	
	private Date createdDate;
	
	private OrgPostMst updatedBy;
	
	private Date updatedDate;
	
	
	// Constructors

	/** default constructor */
	public HrPayCompGrpMst() {
	}

	/** minimal constructor */
	public HrPayCompGrpMst(long Id, OrgUserMst orgUserMstByUserId) {
		this.compoGrpId = compoGrpId;
	}

	public HrPayCompGrpMst(long compoGrpId, long month, long year,
			CmnLocationMst cmnLocationMst, long isactive, Date wefDate,
			String remarks, OrgPostMst createdBy, Date createdDate,
			OrgPostMst updatedBy, Date updatedDate) {
		super();
		this.compoGrpId = compoGrpId;
		this.month = month;
		this.year = year;
		this.cmnLocationMst = cmnLocationMst;
		this.isactive = isactive;
		this.wefDate = wefDate;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public long getCompoGrpId() {
		return compoGrpId;
	}

	public void setCompoGrpId(long compoGrpId) {
		this.compoGrpId = compoGrpId;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public long getIsactive() {
		return isactive;
	}

	public void setIsactive(long isactive) {
		this.isactive = isactive;
	}

	public Date getWefDate() {
		return wefDate;
	}

	public void setWefDate(Date wefDate) {
		this.wefDate = wefDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public OrgPostMst getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(OrgPostMst createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgPostMst getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(OrgPostMst updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}