// default package
// Generated Nov 28, 2007 5:34:18 PM by Hibernate Tools 3.1.0.beta5
package com.tcs.sgv.eis.valueobject;

import java.io.Serializable;

import com.tcs.sgv.ess.valueobject.OrgGradeMst;

/**
 * HrPayArgumentMst generated by hbm2java
 */
public class GpfAcctDtlsVO implements Serializable {

	// Fields    

	private String GpfAcctNo;

	private String empName;
	
	private long userId=0;

	private long empId=0;
	
	private OrgGradeMst orgGradeMst;

	public String getGpfAcctNo() {
		return GpfAcctNo;
	}

	public void setGpfAcctNo(String gpfAcctNo) {
		GpfAcctNo = gpfAcctNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public OrgGradeMst getOrgGradeMst() {
		return orgGradeMst;
	}

	public void setOrgGradeMst(OrgGradeMst orgGradeMst) {
		this.orgGradeMst = orgGradeMst;
	}


}
