package com.tcs.sgv.nps.valueobject;
// default package
// Generated 29 Aug, 2023 5:52:31 PM by Hibernate Tools 5.3.0.Beta2

/**
 * NsdlDhDtls generated by hbm2java
 */
public class NsdlDhDtls implements java.io.Serializable {

	private long dhPk;
	private Integer srNo;
	private String headerName;
	private String dhNo;
	private String dhCol2;
	private String dhDdoRegNo;
	private Long bhSdCount;
	private String dhEmpAmount;
	private String dhEmplrAmount;
	private String fileName;
	private String dhStatus;
	private Character isLegacyData;

	public NsdlDhDtls() {
	}

	public NsdlDhDtls(long dhPk) {
		this.dhPk = dhPk;
	}

	public NsdlDhDtls(long dhPk, Integer srNo, String headerName, String dhNo, String dhCol2, String dhDdoRegNo,
			Long bhSdCount, String dhEmpAmount, String dhEmplrAmount, String fileName, String dhStatus,
			Character isLegacyData) {
		this.dhPk = dhPk;
		this.srNo = srNo;
		this.headerName = headerName;
		this.dhNo = dhNo;
		this.dhCol2 = dhCol2;
		this.dhDdoRegNo = dhDdoRegNo;
		this.bhSdCount = bhSdCount;
		this.dhEmpAmount = dhEmpAmount;
		this.dhEmplrAmount = dhEmplrAmount;
		this.fileName = fileName;
		this.dhStatus = dhStatus;
		this.isLegacyData = isLegacyData;
	}

	public long getDhPk() {
		return this.dhPk;
	}

	public void setDhPk(long dhPk) {
		this.dhPk = dhPk;
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

	public String getDhNo() {
		return this.dhNo;
	}

	public void setDhNo(String dhNo) {
		this.dhNo = dhNo;
	}

	public String getDhCol2() {
		return this.dhCol2;
	}

	public void setDhCol2(String dhCol2) {
		this.dhCol2 = dhCol2;
	}

	public String getDhDdoRegNo() {
		return this.dhDdoRegNo;
	}

	public void setDhDdoRegNo(String dhDdoRegNo) {
		this.dhDdoRegNo = dhDdoRegNo;
	}

	public Long getBhSdCount() {
		return this.bhSdCount;
	}

	public void setBhSdCount(Long bhSdCount) {
		this.bhSdCount = bhSdCount;
	}

	public String getDhEmpAmount() {
		return this.dhEmpAmount;
	}

	public void setDhEmpAmount(String dhEmpAmount) {
		this.dhEmpAmount = dhEmpAmount;
	}

	public String getDhEmplrAmount() {
		return this.dhEmplrAmount;
	}

	public void setDhEmplrAmount(String dhEmplrAmount) {
		this.dhEmplrAmount = dhEmplrAmount;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDhStatus() {
		return this.dhStatus;
	}

	public void setDhStatus(String dhStatus) {
		this.dhStatus = dhStatus;
	}

	public Character getIsLegacyData() {
		return this.isLegacyData;
	}

	public void setIsLegacyData(Character isLegacyData) {
		this.isLegacyData = isLegacyData;
	}

	@Override
	public String toString() {
		return "NsdlDhDtls [dhPk=" + dhPk + ", srNo=" + srNo + ", headerName=" + headerName + ", dhNo=" + dhNo
				+ ", dhCol2=" + dhCol2 + ", dhDdoRegNo=" + dhDdoRegNo + ", bhSdCount=" + bhSdCount + ", dhEmpAmount="
				+ dhEmpAmount + ", dhEmplrAmount=" + dhEmplrAmount + ", fileName=" + fileName + ", dhStatus=" + dhStatus
				+ ", isLegacyData=" + isLegacyData + "]";
	}

}
