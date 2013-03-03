package com.dvlcube.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
public interface ServiceCRUD<T> {
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
	Response<T> list(Integer start, Integer maxResults, List<Order> order);

	/**
	 * @param start
	 * @param maxResults
	 * @param order
	 * @param conditions
	 * @return Conditional results.
	 * @author wonka
	 * @since 22/09/2012
	 */
	Response<T> list(Integer start, Integer maxResults, List<Order> order, Criterion... conditions);

	/**
	 * @param order
	 * @param conditions
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(List<Order> order, Criterion... conditions);

	/**
	 * @param orders
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(Order... orders);

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