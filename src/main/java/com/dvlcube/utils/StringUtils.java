package com.dvlcube.utils;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class StringUtils {
	public static boolean isBlank(final String value) {
		if (value == null) {
			return true;
		}
		return value.isEmpty();
	}
}
