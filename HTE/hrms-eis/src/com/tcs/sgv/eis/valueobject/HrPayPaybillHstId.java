// default package
// Generated Aug 29, 2007 3:52:01 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.eis.valueobject;


/**
 * HrPayPaybillHstId generated by hbm2java
 */
public class HrPayPaybillHstId implements java.io.Serializable {

	// Fields    

	private long id;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HrPayPaybillHstId() {
	}

	/** full constructor */
	public HrPayPaybillHstId(long id, Integer trnCounter) {
		this.id = id;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
		if (!(other instanceof HrPayPaybillHstId))
			return false;
		HrPayPaybillHstId castOther = (HrPayPaybillHstId) other;

		return ((this.getId() == castOther.getId()) || (new Long(this.getId()) != null
				&& new Long(castOther.getId()) != null && new Long(this.getId()).equals(
				castOther.getId())))
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (new Long(getId()) == null ? 0 : new Long(this.getId()).hashCode());
		result = 37 * result + (int) this.getTrnCounter();
		return result;
	}

}
