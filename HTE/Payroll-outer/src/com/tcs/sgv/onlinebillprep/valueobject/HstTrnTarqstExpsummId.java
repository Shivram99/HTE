package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Sep 28, 2007 12:34:44 PM by Hibernate Tools 3.2.0.beta8


/**
 * HstTrnTarqstExpsummId generated by hbm2java
 */
public class HstTrnTarqstExpsummId implements java.io.Serializable {

	// Fields    

	private Long trnTarqstExpsummId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstTrnTarqstExpsummId() {
	}

	/** full constructor */
	public HstTrnTarqstExpsummId(Long trnTarqstExpsummId,
			Integer trnCounter) {
		this.trnTarqstExpsummId = trnTarqstExpsummId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getTrnTarqstExpsummId() {
		return this.trnTarqstExpsummId;
	}

	public void setTrnTarqstExpsummId(Long trnTarqstExpsummId) {
		this.trnTarqstExpsummId = trnTarqstExpsummId;
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
		if (!(other instanceof HstTrnTarqstExpsummId))
			return false;
		HstTrnTarqstExpsummId castOther = (HstTrnTarqstExpsummId) other;

		return ((this.getTrnTarqstExpsummId() == castOther
				.getTrnTarqstExpsummId()) || (this.getTrnTarqstExpsummId() != null
				&& castOther.getTrnTarqstExpsummId() != null && this
				.getTrnTarqstExpsummId().equals(
						castOther.getTrnTarqstExpsummId())))
				&& ((this.getTrnCounter() == castOther.getTrnCounter()) || (this
						.getTrnCounter() != null
						&& castOther.getTrnCounter() != null && this
						.getTrnCounter().equals(castOther.getTrnCounter())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTrnTarqstExpsummId() == null ? 0 : this
						.getTrnTarqstExpsummId().hashCode());
		result = 37
				* result
				+ (getTrnCounter() == null ? 0 : this.getTrnCounter()
						.hashCode());
		return result;
	}

}
