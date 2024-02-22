package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Sep 28, 2007 12:34:44 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnTarqstExpsumm generated by hbm2java
 */
public class HstTrnTarqstExpsumm implements java.io.Serializable {

	// Fields    

	private HstTrnTarqstExpsummId id;

	private Long trnTarqstHdrId;

	private BigDecimal incdntlExp;

	private BigDecimal grossTtl;

	private BigDecimal dedTaAdv;

	private BigDecimal dedPtaDays;

	private BigDecimal dedPta;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private BigDecimal rlwStmFare;

	private BigDecimal ttlDaClmed;

	private BigDecimal netClaimed;

	// Constructors

	/** default constructor */
	public HstTrnTarqstExpsumm() {
	}

	/** minimal constructor */
	public HstTrnTarqstExpsumm(HstTrnTarqstExpsummId id,
			Long trnTarqstHdrId, BigDecimal grossTtl) {
		this.id = id;
		this.trnTarqstHdrId = trnTarqstHdrId;
		this.grossTtl = grossTtl;
	}

	/** full constructor */
	public HstTrnTarqstExpsumm(HstTrnTarqstExpsummId id,
			Long trnTarqstHdrId, BigDecimal incdntlExp,
			BigDecimal grossTtl, BigDecimal dedTaAdv, BigDecimal dedPtaDays,
			BigDecimal dedPta, Long updatedUserId,
			Long updatedPostId, Date updatedDate, BigDecimal rlwStmFare,
			BigDecimal ttlDaClmed, BigDecimal netClaimed) {
		this.id = id;
		this.trnTarqstHdrId = trnTarqstHdrId;
		this.incdntlExp = incdntlExp;
		this.grossTtl = grossTtl;
		this.dedTaAdv = dedTaAdv;
		this.dedPtaDays = dedPtaDays;
		this.dedPta = dedPta;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.rlwStmFare = rlwStmFare;
		this.ttlDaClmed = ttlDaClmed;
		this.netClaimed = netClaimed;
	}

	// Property accessors
	public HstTrnTarqstExpsummId getId() {
		return this.id;
	}

	public void setId(HstTrnTarqstExpsummId id) {
		this.id = id;
	}

	public Long getTrnTarqstHdrId() {
		return this.trnTarqstHdrId;
	}

	public void setTrnTarqstHdrId(Long trnTarqstHdrId) {
		this.trnTarqstHdrId = trnTarqstHdrId;
	}

	public BigDecimal getIncdntlExp() {
		return this.incdntlExp;
	}

	public void setIncdntlExp(BigDecimal incdntlExp) {
		this.incdntlExp = incdntlExp;
	}

	public BigDecimal getGrossTtl() {
		return this.grossTtl;
	}

	public void setGrossTtl(BigDecimal grossTtl) {
		this.grossTtl = grossTtl;
	}

	public BigDecimal getDedTaAdv() {
		return this.dedTaAdv;
	}

	public void setDedTaAdv(BigDecimal dedTaAdv) {
		this.dedTaAdv = dedTaAdv;
	}

	public BigDecimal getDedPtaDays() {
		return this.dedPtaDays;
	}

	public void setDedPtaDays(BigDecimal dedPtaDays) {
		this.dedPtaDays = dedPtaDays;
	}

	public BigDecimal getDedPta() {
		return this.dedPta;
	}

	public void setDedPta(BigDecimal dedPta) {
		this.dedPta = dedPta;
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

	public BigDecimal getRlwStmFare() {
		return this.rlwStmFare;
	}

	public void setRlwStmFare(BigDecimal rlwStmFare) {
		this.rlwStmFare = rlwStmFare;
	}

	public BigDecimal getTtlDaClmed() {
		return this.ttlDaClmed;
	}

	public void setTtlDaClmed(BigDecimal ttlDaClmed) {
		this.ttlDaClmed = ttlDaClmed;
	}

	public BigDecimal getNetClaimed() {
		return this.netClaimed;
	}

	public void setNetClaimed(BigDecimal netClaimed) {
		this.netClaimed = netClaimed;
	}

}
