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

	public CubeString capitalize() {
		string = StringUtils.capitalize(string);
		return this;
	}

	public CubeString decapitalize() {
		string = StringUtils.decapitalize(string);
		return this;
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

	/**
	 * @return converts to long. returns null in case of exception.
	 * @author wonka
	 * @since 26/10/2013
	 */
	public Long l() {
		try {
			return Long.parseLong(string);
		} catch (Exception e) {
			return null;
		}
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
