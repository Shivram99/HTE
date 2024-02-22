package com.tcs.sgv.common.valueobject;

// Generated May 15, 2007 12:29:38 PM by Hibernate Tools 3.2.0.beta8
import java.util.Date;

/**
 * MstEdpReceipt generated by hbm2java
 */
public class MstEdpReceipt implements java.io.Serializable {

	// Fields    

	private long edpId;

	private String edpCode;

	private String edpCodeDesc;

	private String budmjrHd;

	private String budsubmjrHd;

	private String budminHd;

	private String budsubHd;

	private String buddtlHd;

	private String budobjHd;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private long locId;

	private long dbId;

	// Constructors

	/** default constructor */
	public MstEdpReceipt() {
	}

	/** minimal constructor */
	public MstEdpReceipt(long edpId, long createdUserId, long createdPostId) {
		this.edpId = edpId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public MstEdpReceipt(long edpId, String edpCode, String edpCodeDesc,
			String budmjrHd, String budsubmjrHd, String budminHd,
			String budsubHd, String buddtlHd, String budobjHd,
			long createdUserId, long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId, Date updatedDate,
			long locId, long dbId) {
		this.edpId = edpId;
		this.edpCode = edpCode;
		this.edpCodeDesc = edpCodeDesc;
		this.budmjrHd = budmjrHd;
		this.budsubmjrHd = budsubmjrHd;
		this.budminHd = budminHd;
		this.budsubHd = budsubHd;
		this.buddtlHd = buddtlHd;
		this.budobjHd = budobjHd;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locId = locId;
		this.dbId = dbId;
	}

	// Property accessors
	public long getEdpId() {
		return this.edpId;
	}

	public void setEdpId(long edpId) {
		this.edpId = edpId;
	}

	public String getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}

	public String getEdpCodeDesc() {
		return this.edpCodeDesc;
	}

	public void setEdpCodeDesc(String edpCodeDesc) {
		this.edpCodeDesc = edpCodeDesc;
	}

	public String getBudmjrHd() {
		return this.budmjrHd;
	}

	public void setBudmjrHd(String budmjrHd) {
		this.budmjrHd = budmjrHd;
	}

	public String getBudsubmjrHd() {
		return this.budsubmjrHd;
	}

	public void setBudsubmjrHd(String budsubmjrHd) {
		this.budsubmjrHd = budsubmjrHd;
	}

	public String getBudminHd() {
		return this.budminHd;
	}

	public void setBudminHd(String budminHd) {
		this.budminHd = budminHd;
	}

	public String getBudsubHd() {
		return this.budsubHd;
	}

	public void setBudsubHd(String budsubHd) {
		this.budsubHd = budsubHd;
	}

	public String getBuddtlHd() {
		return this.buddtlHd;
	}

	public void setBuddtlHd(String buddtlHd) {
		this.buddtlHd = buddtlHd;
	}

	public String getBudobjHd() {
		return this.budobjHd;
	}

	public void setBudobjHd(String budobjHd) {
		this.budobjHd = budobjHd;
	}

	public long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

}
