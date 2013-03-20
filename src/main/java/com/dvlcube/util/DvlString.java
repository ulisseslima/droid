package com.dvlcube.util;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class DvlString {
	private final String string;

	/**
	 * @param string
	 * @author wonka
	 * @since 28/02/2013
	 */
	public DvlString(final String string) {
		super();
		this.string = string;
	}

	public boolean isBlank() {
		return StringUtils.isBlank(string);
	}

	public boolean isNotBlank() {
		return !isBlank();
	}
}
