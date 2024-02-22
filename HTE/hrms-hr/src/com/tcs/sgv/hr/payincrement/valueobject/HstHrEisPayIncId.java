package com.tcs.sgv.hr.payincrement.valueobject;

// default package
// Generated Oct 9, 2007 12:45:08 PM by Hibernate Tools 3.2.0.beta8


/**
 * HstHrEisPayIncId generated by hbm2java
 */
public class HstHrEisPayIncId implements java.io.Serializable {

	// Fields    

	private Long userId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstHrEisPayIncId() {
	}

	/** full constructor */
	public HstHrEisPayIncId(Long userId, Integer trnCounter) {
		this.userId = userId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HstHrEisPayIncId))
			return false;
		HstHrEisPayIncId castOther = (HstHrEisPayIncId) other;

		return ((this.getUserId()) == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId()))
				&& ((this.getTrnCounter() == castOther.getTrnCounter()) || (this
						.getTrnCounter() != null
						&& castOther.getTrnCounter() != null && this
						.getTrnCounter().equals(castOther.getTrnCounter())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getTrnCounter() == null ? 0 : this.getTrnCounter()
						.hashCode());
		return result;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
