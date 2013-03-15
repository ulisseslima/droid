package com.dvlcube.utils;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class StringUtils {
	/**
	 * @param title
	 * @return
	 * @author wonka
	 * @since 15/03/2013
	 */
	public static String escapeHTML(final String title) {
		return title.replace("\"", "&quot;");
	}

	public static boolean isBlank(final String value) {
		if (value == null) {
			return true;
		}
		return value.isEmpty();
	}
}
