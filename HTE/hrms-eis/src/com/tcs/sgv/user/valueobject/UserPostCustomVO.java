package com.tcs.sgv.user.valueobject;

// default package
// Generated Oct 9, 2007 12:44:58 PM by Hibernate Tools 3.2.0.beta8


/**
 * HrPayOrderHeadCustomMpg generated by hbm2java
 */

@SuppressWarnings("serial")
public class UserPostCustomVO implements java.io.Serializable {

	// Fields    

	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private long empuserId;
	
	private long empId;
	
	private long userId;
	
	private long postId;
	
	private long dsgnId;
	
	private long userPostId;
	
	private String postname;
	
	private String empFname;
	
	private String empMname;
	
	private String empLname;
	
	private String empFullName;
	
	private String PsrNo;
	
	private String billNo;
	
	private String StartDate;
	
	private String EndDate;
	
	private String dsgnname;
	
	private String userName;
	
	private String postType;
	
	private String sevaarthId;
	
	private String netSalary;
	
	private String ddoCode;
	
	private String subjectName;
	
	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String lStrbillNo) {
		billNo = lStrbillNo;
	}

	public String getPsrNo() {
		return PsrNo;
	}

	public void setPsrNo(String psrNo) {
		PsrNo = psrNo;
	}
	
	public String getEmpFullName() {
		return empFullName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	
	
	

	public long getDsgnId() {
		return dsgnId;
	}

	public void setDsgnId(long dsgnId) {
		this.dsgnId = dsgnId;
	}

	public String getDsgnname() {
		return dsgnname;
	}

	public void setDsgnname(String dsgnname) {
		this.dsgnname = dsgnname;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public String getEmpMname() {
		return empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}
	
	public long getEmpuserId() {
		return empuserId;
	}

	public void setEmpuserId(long empuserId) {
		this.empuserId = empuserId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(long userPostId) {
		this.userPostId = userPostId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the postType
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * @param postType the postType to set
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getSevaarthId() {
		return sevaarthId;
	}

	public void setSevaarthId(String sevaarthId) {
		this.sevaarthId = sevaarthId;
	}

	public String getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}
	
	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
		
	
		
}

	