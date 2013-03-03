package com.dvlcube.bean;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
public enum Privacy implements Describable {
	PRIVATE, PUBLIC;

	@Override
	public String getDescription() {
		return "missing";
	}

	@Override
	public String getTitle() {
		return name();
	}

	@Override
	public void setDescription(final String description) {
	}

	@Override
	public void setTitle(final String title) {
	}
}
