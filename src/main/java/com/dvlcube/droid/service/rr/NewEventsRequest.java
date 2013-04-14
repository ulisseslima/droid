package com.dvlcube.droid.service.rr;

import static com.dvlcube.util.Cuber.$;

/**
 * A request for Events that were updated recently.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class NewEventsRequest {
	private String focusedEventName;
	private long lastUpdate;
	private long parentId;

	/**
	 * @return the focusedEventName
	 */
	public String getFocusedEventName() {
		return focusedEventName;
	}

	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * @param focusedEventName
	 *            the focusedEventName to set
	 */
	public void setFocusedEventName(final String focusedEventName) {
		this.focusedEventName = focusedEventName;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(final long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return $(this).stringify();
	}
}
