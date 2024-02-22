package com.tcs.sgv.common.helper;

public enum SameSite {
	
	/**
	 * Cookies are not sent on normal cross-site subrequests (for example to load images or frames into a third party site), 
	 * but are sent when a user is navigating to the origin site (i.e. when following a link).
	 * 
	 * This is the (new) default if SameSite is not specified.
	 * (Previously the default was None (cookies sent for all requests))
	 */
	LAX("Lax"),
	/**
	 * Cookies will be sent in all contexts, i.e in responses to both first-party and cross-origin requests.
	 * If SameSite=None is set, the cookie Secure attribute must also be set (or the cookie will be blocked)
	 */
	NONE("None"),
	/**
	 * Cookies will only be sent in a first-party context and not be sent along with requests initiated by third party websites.
	 */
	STRICT("Strict");
	
	private String value;
	
	private SameSite(String value) {
		this.value=value;
	}

	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}