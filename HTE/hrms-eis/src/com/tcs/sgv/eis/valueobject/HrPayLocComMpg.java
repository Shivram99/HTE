package com.tcs.sgv.eis.valueobject;

// default package
// Generated Feb 8, 2008 7:42:29 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLookupMst;

/**
 * HrEnqEmpDtl generated by hbm2java
 */
public class HrPayLocComMpg implements java.io.Serializable {

	// Fields    

	private long Id;

	private CmnLookupMst cmnLookupMst;

	private long compId;
	
	private long isactive;
	
	private HrPayCompGrpMst hrpaycompgrpmst;
	
	private long updatedByPost;
	
	private Date updatedDate; 
	
	public long getUpdatedByPost()
	{
		return updatedByPost;
	}

	public void setUpdatedByPost(long updatedByPost)
	{
		this.updatedByPost = updatedByPost;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public CmnLookupMst getCmnLookupMst() {
		return cmnLookupMst;
	}

	public void setCmnLookupMst(CmnLookupMst cmnLookupMst) {
		this.cmnLookupMst = cmnLookupMst;
	}

	public long getCompId() {
		return compId;
	}

	public void setCompId(long compId) {
		this.compId = compId;
	}

	public long getIsactive() {
		return isactive;
	}

	public void setIsactive(long isactive) {
		this.isactive = isactive;
	}

	public HrPayCompGrpMst getHrpaycompgrpmst() {
		return hrpaycompgrpmst;
	}

	public void setHrpaycompgrpmst(HrPayCompGrpMst hrpaycompgrpmst) {
		this.hrpaycompgrpmst = hrpaycompgrpmst;
	}
							
}
