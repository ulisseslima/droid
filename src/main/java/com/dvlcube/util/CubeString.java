package com.dvlcube.util;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class CubeString {
	public final String string;

	/**
	 * @param string
	 * @author wonka
	 * @since 28/02/2013
	 */
	public CubeString(final String string) {
		super();
		this.string = string;
	}

	public CubeString escapeHTML() {
		return new CubeString(StringUtils.escapeHTML(string));
	}

	public boolean isBlank() {
		return StringUtils.isBlank(string);
	}

	public boolean isNotBlank() {
		return !isBlank();
	}
}
