package com.dvlcube.bean;

/**
 * @param <P>
 *            Parent type.
 * @author wonka
 * @since 29/03/2013
 */
public interface Child<P> {
	/**
	 * @return
	 * @author wonka
	 * @since 29/03/2013
	 */
	P getParent();

	/**
	 * @param parent
	 * @author wonka
	 * @since 29/03/2013
	 */
	void setParent(P parent);
}
