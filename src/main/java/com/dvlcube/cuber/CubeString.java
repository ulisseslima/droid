package com.dvlcube.cuber;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class CubeString {
	public String string;

	/**
	 * @param string
	 * @author wonka
	 * @since 28/02/2013
	 */
	public CubeString(final String string) {
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

	public CubeString scramble() {
		string = StringUtils.scramble(string);
		return this;
	}

	@Override
	public String toString() {
		return string;
	}
}
