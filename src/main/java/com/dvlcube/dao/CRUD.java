package com.dvlcube.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
public interface CRUD<E> {
	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Serializable create(E entity);

	/**
	 * @param entity
	 * @author wonka
	 * @since 13/09/2012
	 */
	void delete(E entity);

	/**
	 * @param start
	 * @param maxResults
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	List<E> list(Integer start, Integer maxResults);

	/**
	 * @param id
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	E retrieve(long id);

	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	E update(E entity);
}