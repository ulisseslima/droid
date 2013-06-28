package com.dvlcube.droid.service.rr;

import static com.dvlcube.cuber.Cuber.$;

/**
 * @author wonka
 * @since 10/04/2013
 */
public class NewParticipantRequest {
	private Long listingId;
	private String name;

	/**
	 * @return the listingId
	 */
	public Long getListingId() {
		return listingId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param listingId
	 *            the listingId to set
	 */
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return $(this).stringify();
	}
}
