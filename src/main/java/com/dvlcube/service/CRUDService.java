package com.dvlcube.service;

import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
public interface CRUDService<T extends BasicInfo> {
	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> add(T entity);

	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 27/02/2013
	 */
	Response<T> addOrUpdate(T entity);

	/**
	 * @param id
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> delete(long id);

	/**
	 * @param entity
	 * @author wonka
	 * @since 13/09/2012
	 */
	Response<T> delete(T entity);

	/**
	 * @param id
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> get(long id);

	/**
	 * @param t
	 *            An object with the desired parameters.
	 * @return The first saved object that matches the properties in t.
	 * @author wonka
	 * @since 27/02/2013
	 */
	Response<T> get(T t);

	/**
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> list();

	/**
	 * @param orders
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(Criterion... orders);

	/**
	 * @param start
	 * @param maxResults
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> list(Integer start, Integer maxResults);

	/**
	 * @param start
	 * @param maxResults
	 * @param order
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(Integer start, Integer maxResults, Set<Order> order);

	/**
	 * @param start
	 * @param maxResults
	 * @param order
	 * @param conditions
	 * @return Conditional results.
	 * @author wonka
	 * @since 22/09/2012
	 */
	Response<T> list(Integer start, Integer maxResults, Set<Order> order, Criterion... conditions);

	/**
	 * @param orders
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(Order... orders);

	/**
	 * @param order
	 * @param conditions
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(Set<Order> order, Criterion... conditions);

	/**
	 * @param desc
	 * @return
	 * @author wonka
	 * @since 28/03/2013
	 */
	Response<T> listByDateModified(boolean desc);

	/**
	 * @return
	 * @author wonka
	 * @since 28/03/2013
	 */
	Response<T> listOldFirst();

	/**
	 * @return
	 * @author wonka
	 * @since 28/03/2013
	 */
	Response<T> listRecentFirst();

	/**
	 * @param id
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> update(long id);

	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> update(T entity);
}