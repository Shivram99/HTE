package com.tcs.sgv.nps.valueobject;
// default package
// Generated 29 Aug, 2023 5:52:31 PM by Hibernate Tools 5.3.0.Beta2

import java.sql.Clob;
import java.util.Date;

/**
 * NsdlBhDtls generated by hbm2java
 */ 
public class NsdlBhDtls implements java.io.Serializable {

	private long bhPk;
	private Integer srNo;
	private String headerName;
	private Character bhNo;
	private Character bhCol2;
	private String bhFixNo;
	private String bhDate;
	private String bhBatchFixId;
	private Integer bhDdoCount;
	private String bhPranCount;
	private String bhEmpAmount;
	private String bhEmplrAmount;
	private String bhTotalAmt;
	private String fileName;
	private Short year;
	private Short month;
	private String status;
	private String nsdlFileHash;
	private String fileStatus;
	private String transactionId;
	private Clob errorData;
	private String oldTransactionId;
	private String reasonForTranIdUpdatevar;
	private String remarkForTranIdUpdate;
	private String frnNo;
	private Character isLegacyData;
	private String ddoCode;
	private Date fileCreatedDate;
	private Date fileUploadCreatedDate;
	private Date challanReceivedCreatedDate;
	private String bankRefno;
	private Long voucherNo;
	private Date voucherDate;
	private String bdsNo;

	public NsdlBhDtls() {
	}

	public NsdlBhDtls(long bhPk, String bhBatchFixId) {
		this.bhPk = bhPk;
		this.bhBatchFixId = bhBatchFixId;
	}

	public NsdlBhDtls(long bhPk, Integer srNo, String headerName, Character bhNo, Character bhCol2, String bhFixNo,
			String bhDate, String bhBatchFixId, Integer bhDdoCount, String bhPranCount, String bhEmpAmount,
			String bhEmplrAmount, String bhTotalAmt, String fileName, Short year, Short month, String status,
			String nsdlFileHash, String fileStatus, String transactionId, Clob errorData, String oldTransactionId,
			String reasonForTranIdUpdatevar, String remarkForTranIdUpdate, String frnNo, Character isLegacyData,
			String ddoCode, Date fileCreatedDate, Date fileUploadCreatedDate, Date challanReceivedCreatedDate,
			String bankRefno, Long voucherNo, Date voucherDate, String bdsNo) {
		this.bhPk = bhPk;
		this.srNo = srNo;
		this.headerName = headerName;
		this.bhNo = bhNo;
		this.bhCol2 = bhCol2;
		this.bhFixNo = bhFixNo;
		this.bhDate = bhDate;
		this.bhBatchFixId = bhBatchFixId;
		this.bhDdoCount = bhDdoCount;
		this.bhPranCount = bhPranCount;
		this.bhEmpAmount = bhEmpAmount;
		this.bhEmplrAmount = bhEmplrAmount;
		this.bhTotalAmt = bhTotalAmt;
		this.fileName = fileName;
		this.year = year;
		this.month = month;
		this.status = status;
		this.nsdlFileHash = nsdlFileHash;
		this.fileStatus = fileStatus;
		this.transactionId = transactionId;
		this.errorData = errorData;
		this.oldTransactionId = oldTransactionId;
		this.reasonForTranIdUpdatevar = reasonForTranIdUpdatevar;
		this.remarkForTranIdUpdate = remarkForTranIdUpdate;
		this.frnNo = frnNo;
		this.isLegacyData = isLegacyData;
		this.ddoCode = ddoCode;
		this.fileCreatedDate = fileCreatedDate;
		this.fileUploadCreatedDate = fileUploadCreatedDate;
		this.challanReceivedCreatedDate = challanReceivedCreatedDate;
		this.bankRefno = bankRefno;
		this.voucherNo = voucherNo;
		this.voucherDate = voucherDate;
		this.bdsNo = bdsNo;
	}

	public long getBhPk() {
		return this.bhPk;
	}

	public void setBhPk(long bhPk) {
		this.bhPk = bhPk;
	}

	public Integer getSrNo() {
		return this.srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getHeaderName() {
		return this.headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public Character getBhNo() {
		return this.bhNo;
	}

	public void setBhNo(Character bhNo) {
		this.bhNo = bhNo;
	}

	public Character getBhCol2() {
		return this.bhCol2;
	}

	public void setBhCol2(Character bhCol2) {
		this.bhCol2 = bhCol2;
	}

	public String getBhFixNo() {
		return this.bhFixNo;
	}

	public void setBhFixNo(String bhFixNo) {
		this.bhFixNo = bhFixNo;
	}

	public String getBhDate() {
		return this.bhDate;
	}

	public void setBhDate(String bhDate) {
		this.bhDate = bhDate;
	}

	public String getBhBatchFixId() {
		return this.bhBatchFixId;
	}

	public void setBhBatchFixId(String bhBatchFixId) {
		this.bhBatchFixId = bhBatchFixId;
	}

	public Integer getBhDdoCount() {
		return this.bhDdoCount;
	}

	public void setBhDdoCount(Integer bhDdoCount) {
		this.bhDdoCount = bhDdoCount;
	}

	public String getBhPranCount() {
		return this.bhPranCount;
	}

	public void setBhPranCount(String bhPranCount) {
		this.bhPranCount = bhPranCount;
	}

	public String getBhEmpAmount() {
		return this.bhEmpAmount;
	}

	public void setBhEmpAmount(String bhEmpAmount) {
		this.bhEmpAmount = bhEmpAmount;
	}

	public String getBhEmplrAmount() {
		return this.bhEmplrAmount;
	}

	public void setBhEmplrAmount(String bhEmplrAmount) {
		this.bhEmplrAmount = bhEmplrAmount;
	}

	public String getBhTotalAmt() {
		return this.bhTotalAmt;
	}

	public void setBhTotalAmt(String bhTotalAmt) {
		this.bhTotalAmt = bhTotalAmt;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Short getYear() {
		return this.year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public Short getMonth() {
		return this.month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNsdlFileHash() {
		return this.nsdlFileHash;
	}

	public void setNsdlFileHash(String nsdlFileHash) {
		this.nsdlFileHash = nsdlFileHash;
	}

	public String getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Clob getErrorData() {
		return this.errorData;
	}

	public void setErrorData(Clob errorData) {
		this.errorData = errorData;
	}

	public String getOldTransactionId() {
		return this.oldTransactionId;
	}

	public void setOldTransactionId(String oldTransactionId) {
		this.oldTransactionId = oldTransactionId;
	}

	public String getReasonForTranIdUpdatevar() {
		return this.reasonForTranIdUpdatevar;
	}

	public void setReasonForTranIdUpdatevar(String reasonForTranIdUpdatevar) {
		this.reasonForTranIdUpdatevar = reasonForTranIdUpdatevar;
	}

	public String getRemarkForTranIdUpdate() {
		return this.remarkForTranIdUpdate;
	}

	public void setRemarkForTranIdUpdate(String remarkForTranIdUpdate) {
		this.remarkForTranIdUpdate = remarkForTranIdUpdate;
	}

	public String getFrnNo() {
		return this.frnNo;
	}

	public void setFrnNo(String frnNo) {
		this.frnNo = frnNo;
	}

	public Character getIsLegacyData() {
		return this.isLegacyData;
	}

	public void setIsLegacyData(Character isLegacyData) {
		this.isLegacyData = isLegacyData;
	}

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public Date getFileCreatedDate() {
		return this.fileCreatedDate;
	}

	public void setFileCreatedDate(Date fileCreatedDate) {
		this.fileCreatedDate = fileCreatedDate;
	}

	public Date getFileUploadCreatedDate() {
		return this.fileUploadCreatedDate;
	}

	public void setFileUploadCreatedDate(Date fileUploadCreatedDate) {
		this.fileUploadCreatedDate = fileUploadCreatedDate;
	}

	public Date getChallanReceivedCreatedDate() {
		return this.challanReceivedCreatedDate;
	}

	public void setChallanReceivedCreatedDate(Date challanReceivedCreatedDate) {
		this.challanReceivedCreatedDate = challanReceivedCreatedDate;
	}

	public String getBankRefno() {
		return this.bankRefno;
	}

	public void setBankRefno(String bankRefno) {
		this.bankRefno = bankRefno;
	}

	public Long getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(Long voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getVoucherDate() {
		return this.voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getBdsNo() {
		return this.bdsNo;
	}

	public void setBdsNo(String bdsNo) {
		this.bdsNo = bdsNo;
	}

	
	@Override
	public String toString() {
		return "NsdlBhDtls [bhPk=" + bhPk + ", srNo=" + srNo + ", headerName=" + headerName + ", bhNo=" + bhNo
				+ ", bhCol2=" + bhCol2 + ", bhFixNo=" + bhFixNo + ", bhDate=" + bhDate + ", bhBatchFixId="
				+ bhBatchFixId + ", bhDdoCount=" + bhDdoCount + ", bhPranCount=" + bhPranCount + ", bhEmpAmount="
				+ bhEmpAmount + ", bhEmplrAmount=" + bhEmplrAmount + ", bhTotalAmt=" + bhTotalAmt + ", fileName="
				+ fileName + ", year=" + year + ", month=" + month + ", status=" + status + ", nsdlFileHash="
				+ nsdlFileHash + ", fileStatus=" + fileStatus + ", transactionId=" + transactionId + ", errorData="
				+ errorData + ", oldTransactionId=" + oldTransactionId + ", reasonForTranIdUpdatevar="
				+ reasonForTranIdUpdatevar + ", remarkForTranIdUpdate=" + remarkForTranIdUpdate + ", frnNo=" + frnNo
				+ ", isLegacyData=" + isLegacyData + ", ddoCode=" + ddoCode + ", fileCreatedDate=" + fileCreatedDate
				+ ", fileUploadCreatedDate=" + fileUploadCreatedDate + ", challanReceivedCreatedDate="
				+ challanReceivedCreatedDate + ", bankRefno=" + bankRefno + ", voucherNo=" + voucherNo
				+ ", voucherDate=" + voucherDate + ", bdsNo=" + bdsNo + "]";
	}

}
