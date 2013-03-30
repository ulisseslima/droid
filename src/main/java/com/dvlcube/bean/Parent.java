package com.dvlcube.bean;

/**
 * @param <C>
 * @author wonka
 * @since 29/03/2013
 */
public interface Parent<C> {
	/**
	 * @return
	 * @author wonka
	 * @since 29/03/2013
	 */
	C getChild();

	/**
	 * @param child
	 * @author wonka
	 * @since 29/03/2013
	 */
	void setChild(C child);
}
