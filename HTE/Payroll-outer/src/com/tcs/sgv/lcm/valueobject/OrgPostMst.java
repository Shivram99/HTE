package com.tcs.sgv.lcm.valueobject;

// Generated Oct 17, 2007 7:28:34 PM by Hibernate Tools 3.2.0.beta8


/**
 * OrgPostMst generated by hbm2java
 */
public class OrgPostMst  {

	// Fields    

	private Long postId;

	private Long UpdatedByPost;

	private Long CreatedByPost;

	private Long parentPostId;

	private Long postLevelId;

	private Long statusLookupId;

	private String startDate;

	private String endDate;

	private Long activateFlag;

	private Long createdBy;

	private String createdDate;

	private Long updatedBy;

	private String updatedDate;

	private String locationCode;

	private String branchCode;

	private String dsgnCode;

	public Long getActivateFlag() {
		return activateFlag;
	}

	public void setActivateFlag(Long activateFlag) {
		this.activateFlag = activateFlag;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedByPost() {
		return CreatedByPost;
	}

	public void setCreatedByPost(Long createdByPost) {
		CreatedByPost = createdByPost;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDsgnCode() {
		return dsgnCode;
	}

	public void setDsgnCode(String dsgnCode) {
		this.dsgnCode = dsgnCode;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getParentPostId() {
		return parentPostId;
	}

	public void setParentPostId(Long parentPostId) {
		this.parentPostId = parentPostId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getPostLevelId() {
		return postLevelId;
	}

	public void setPostLevelId(Long postLevelId) {
		this.postLevelId = postLevelId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Long getStatusLookupId() {
		return statusLookupId;
	}

	public void setStatusLookupId(Long statusLookupId) {
		this.statusLookupId = statusLookupId;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedByPost() {
		return UpdatedByPost;
	}

	public void setUpdatedByPost(Long updatedByPost) {
		UpdatedByPost = updatedByPost;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	

}
