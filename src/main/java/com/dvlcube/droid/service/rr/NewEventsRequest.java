package com.dvlcube.droid.service.rr;

import static com.dvlcube.cuber.Cuber.$;

/**
 * A request for Events that were updated recently.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class NewEventsRequest extends AsyncRequest {
	private String focusedEventName;
	private long parentId;

	/**
	 * @return the focusedEventName
	 */
	public String getFocusedEventName() {
		return focusedEventName;
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
