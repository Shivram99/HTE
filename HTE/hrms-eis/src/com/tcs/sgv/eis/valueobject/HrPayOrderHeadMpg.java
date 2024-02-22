package com.tcs.sgv.eis.valueobject;

// default package
// Generated Oct 9, 2007 12:44:58 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrPayOrderHeadMpg generated by hbm2java
 */
public class HrPayOrderHeadMpg implements java.io.Serializable {

	// Fields    

	private long orderHeadId;

	private long orderId;

	private String subheadId;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private Date updatedDate;
	
	private Integer trnCounter;
	// Constructors

	/** default constructor */
	public HrPayOrderHeadMpg() {
	}

	/** minimal constructor */
	public HrPayOrderHeadMpg(long orderHeadId) {
		this.orderHeadId = orderHeadId;
	}

	/** full constructor */
	public HrPayOrderHeadMpg(long orderHeadId, long orderId,
			String subheadId, 			OrgUserMst orgUserMstByCreatedBy , OrgPostMst orgPostMstByCreatedByPost, Date createdDate,
			OrgUserMst orgUserMstByUpdatedBy, OrgPostMst orgPostMstByUpdatedByPost , Date updatedDate ,Integer trnCounter) {
		this.orderHeadId = orderHeadId;
		this.orderId = orderId;
		this.subheadId = subheadId;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.createdDate = createdDate;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.updatedDate = updatedDate;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getOrderHeadId() {
		return this.orderHeadId;
	}

	public void setOrderHeadId(long orderHeadId) {
		this.orderHeadId = orderHeadId;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getSubheadId() {
		return this.subheadId;
	}

	public void setSubheadId(String subheadId) {
		this.subheadId = subheadId;
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
	

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}
	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}
	
	

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}
	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}
	
	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}
	
	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	
	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}
	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}
	public Integer getTrnCounter() {
		return trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}
}
