package com.dvlcube.droid.service.rr;

/**
 * A request for Events that were updated recently.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class NewEventsRequest {
	private String focusedEventTitle;
	private long lastUpdate;

	/**
	 * @return the focusedEventTitle
	 */
	public String getFocusedEventTitle() {
		return focusedEventTitle;
	}

	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param focusedEventTitle
	 *            the focusedEventTitle to set
	 */
	public void setFocusedEventTitle(final String focusedEventTitle) {
		this.focusedEventTitle = focusedEventTitle;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(final long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "NewEventsRequest [focusedEventTitle=" + focusedEventTitle + ", lastUpdate=" + lastUpdate
				+ "]";
	}
}
