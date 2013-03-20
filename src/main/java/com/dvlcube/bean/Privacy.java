package com.dvlcube.bean;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
public enum Privacy {
	PRIVATE, PUBLIC;

	public String getDescription() {
		return "missing";
	}

	public String getTitle() {
		return name();
	}

	public void setDescription(final String description) {
	}

	public void setTitle(final String title) {
	}
}
