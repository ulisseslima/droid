package com.dvlcube.droid.service.rr;

import com.dvlcube.util.StringUtils;

/**
 * A request for Events that were updated recently.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class NewEventsRequest {
	private String focusedEventTitle;
	private long lastUpdate;
	private long listingId;

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
	 * @return the listingId
	 */
	public long getListingId() {
		return listingId;
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

	/**
	 * @param listingId
	 *            the listingId to set
	 */
	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	@Override
	public String toString() {
		return StringUtils.stringify(this);
	}
}
