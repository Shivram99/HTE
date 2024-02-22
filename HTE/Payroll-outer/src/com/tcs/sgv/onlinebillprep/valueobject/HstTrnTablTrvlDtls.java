package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Sep 28, 2007 12:33:16 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnTablTrvlDtls generated by hbm2java
 */
public class HstTrnTablTrvlDtls implements java.io.Serializable {

	// Fields    

	private HstTrnTablTrvlDtlsId id;

	private Long trnTablHdrId;

	private short orderNo;

	private String depStation;

	private Date depDate;

	private String depHour;

	private String arrStation;

	private Date arrDate;

	private String arrHour;

	private String trvlMode;

	private String fareClass;

	private Integer fareNumber;

	private BigDecimal fareAmt;

	private BigDecimal admsblOrdRate;

	private BigDecimal admsblOthRate;

	private BigDecimal admsblDa;

	private BigDecimal numOfDaysClmed;

	private String purpose;

	private BigDecimal totalAmt;

	private String remarks;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String locationCode;

	// Constructors

	/** default constructor */
	public HstTrnTablTrvlDtls() {
	}

	/** minimal constructor */
	public HstTrnTablTrvlDtls(HstTrnTablTrvlDtlsId id, Long trnTablHdrId,
			short orderNo, BigDecimal fareAmt, BigDecimal admsblOrdRate,
			BigDecimal admsblOthRate, BigDecimal admsblDa,
			Long createdUserId, Long createdPostId,
			Date createdDate, Long dbId, String locationCode) {
		this.id = id;
		this.trnTablHdrId = trnTablHdrId;
		this.orderNo = orderNo;
		this.fareAmt = fareAmt;
		this.admsblOrdRate = admsblOrdRate;
		this.admsblOthRate = admsblOthRate;
		this.admsblDa = admsblDa;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public HstTrnTablTrvlDtls(HstTrnTablTrvlDtlsId id, Long trnTablHdrId,
			short orderNo, String depStation, Date depDate,
			String depHour, String arrStation, Date arrDate,
			String arrHour, String trvlMode,
			String fareClass, Integer fareNumber, BigDecimal fareAmt,
			BigDecimal admsblOrdRate, BigDecimal admsblOthRate,
			BigDecimal admsblDa, BigDecimal numOfDaysClmed,
			String purpose, BigDecimal totalAmt, String remarks,
			Long createdUserId, Long createdPostId,
			Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			String locationCode) {
		this.id = id;
		this.trnTablHdrId = trnTablHdrId;
		this.orderNo = orderNo;
		this.depStation = depStation;
		this.depDate = depDate;
		this.depHour = depHour;
		this.arrStation = arrStation;
		this.arrDate = arrDate;
		this.arrHour = arrHour;
		this.trvlMode = trvlMode;
		this.fareClass = fareClass;
		this.fareNumber = fareNumber;
		this.fareAmt = fareAmt;
		this.admsblOrdRate = admsblOrdRate;
		this.admsblOthRate = admsblOthRate;
		this.admsblDa = admsblDa;
		this.numOfDaysClmed = numOfDaysClmed;
		this.purpose = purpose;
		this.totalAmt = totalAmt;
		this.remarks = remarks;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	// Property accessors
	public HstTrnTablTrvlDtlsId getId() {
		return this.id;
	}

	public void setId(HstTrnTablTrvlDtlsId id) {
		this.id = id;
	}

	public Long getTrnTablHdrId() {
		return this.trnTablHdrId;
	}

	public void setTrnTablHdrId(Long trnTablHdrId) {
		this.trnTablHdrId = trnTablHdrId;
	}

	public short getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(short orderNo) {
		this.orderNo = orderNo;
	}

	public String getDepStation() {
		return this.depStation;
	}

	public void setDepStation(String depStation) {
		this.depStation = depStation;
	}

	public Date getDepDate() {
		return this.depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public String getDepHour() {
		return this.depHour;
	}

	public void setDepHour(String depHour) {
		this.depHour = depHour;
	}

	public String getArrStation() {
		return this.arrStation;
	}

	public void setArrStation(String arrStation) {
		this.arrStation = arrStation;
	}

	public Date getArrDate() {
		return this.arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public String getArrHour() {
		return this.arrHour;
	}

	public void setArrHour(String arrHour) {
		this.arrHour = arrHour;
	}

	public String getTrvlMode() {
		return this.trvlMode;
	}

	public void setTrvlMode(String trvlMode) {
		this.trvlMode = trvlMode;
	}

	public String getFareClass() {
		return this.fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public Integer getFareNumber() {
		return this.fareNumber;
	}

	public void setFareNumber(Integer fareNumber) {
		this.fareNumber = fareNumber;
	}

	public BigDecimal getFareAmt() {
		return this.fareAmt;
	}

	public void setFareAmt(BigDecimal fareAmt) {
		this.fareAmt = fareAmt;
	}

	public BigDecimal getAdmsblOrdRate() {
		return this.admsblOrdRate;
	}

	public void setAdmsblOrdRate(BigDecimal admsblOrdRate) {
		this.admsblOrdRate = admsblOrdRate;
	}

	public BigDecimal getAdmsblOthRate() {
		return this.admsblOthRate;
	}

	public void setAdmsblOthRate(BigDecimal admsblOthRate) {
		this.admsblOthRate = admsblOthRate;
	}

	public BigDecimal getAdmsblDa() {
		return this.admsblDa;
	}

	public void setAdmsblDa(BigDecimal admsblDa) {
		this.admsblDa = admsblDa;
	}

	public BigDecimal getNumOfDaysClmed() {
		return this.numOfDaysClmed;
	}

	public void setNumOfDaysClmed(BigDecimal numOfDaysClmed) {
		this.numOfDaysClmed = numOfDaysClmed;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public BigDecimal getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
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

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
