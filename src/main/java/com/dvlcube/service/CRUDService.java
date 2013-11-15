package com.dvlcube.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.dvlcube.bean.Child;
import com.dvlcube.droid.service.rr.AsyncRequest;

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

	String getEntityPackage();

	/**
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> list();

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param orders
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(AsyncRequest asyncRequest, Criterion... orders);

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param start
	 * @param maxResults
	 * @return
	 * @author wonka
	 * @since 15/09/2012
	 */
	Response<T> list(AsyncRequest asyncRequest, Integer start, Integer maxResults);

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param start
	 * @param maxResults
	 * @param order
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(AsyncRequest asyncRequest, Integer start, Integer maxResults, Set<Order> order);

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param start
	 * @param maxResults
	 * @param order
	 * @param conditions
	 * @return Conditional results.
	 * @author wonka
	 * @since 22/09/2012
	 */
	Response<T> list(
		AsyncRequest asyncRequest,
		Integer start,
		Integer maxResults,
		Set<Order> order,
		Criterion... conditions);

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param orders
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(AsyncRequest asyncRequest, Order... orders);

	/**
	 * @param asyncRequest
	 *            TODO
	 * @param order
	 * @param conditions
	 * @return
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<T> list(AsyncRequest asyncRequest, Set<Order> order, Criterion... conditions);

	/**
	 * @param restrictions
	 * @return
	 * @author wonka
	 * @since 15/11/2013
	 */
	Response<T> list(Criterion... restrictions);

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
	 * @param child
	 * @return siblings list.
	 * @author wonka
	 * @since 26/10/2013
	 */
	List<String> listSiblings(Child<? extends BasicInfo> child);

	/**
	 * Splits the string, creates a new instance using the entity package, and uses the id to load.
	 * 
	 * @param entity_id
	 *            string in the format "entityName-id".
	 * @return an entity with the id specified.
	 * @author wonka
	 * @since 26/10/2013
	 */
	BasicInfo load(String entity_id);

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