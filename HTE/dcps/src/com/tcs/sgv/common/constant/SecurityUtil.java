package com.tcs.sgv.common.constant;


import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@SuppressWarnings("deprecation")
public class SecurityUtil {
	
	private SecurityUtil() {
	        throw new IllegalStateException("Utility class");
	    }

	    @SuppressWarnings("deprecation")
		public static String cleanIt(String str) {
	    	String safe = Jsoup.clean(str, Whitelist.simpleText());
			return safe;
	    }

}



