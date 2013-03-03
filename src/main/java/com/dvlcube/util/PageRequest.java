package com.dvlcube.util;

/**
 * 
 * @author wonka
 * @since 22/09/2012
 */
public class PageRequest implements Request {
	private int maxResults;
	private int start;

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(final int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(final int start) {
		this.start = start;
	}
}
