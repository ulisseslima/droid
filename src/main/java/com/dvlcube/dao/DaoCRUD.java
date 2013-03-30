package com.dvlcube.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.dvlcube.bean.Identifiable;
import com.dvlcube.droid.bean.User;

/**
 * @author wonka
 * @since 11/08/2012
 */
public interface DaoCRUD<E extends Identifiable> extends CRUD<E> {

	/**
	 * @param entity
	 * @param id
	 * @return
	 * @author wonka
	 * @since 13/09/2012
	 */
	boolean delete(Class<E> entity, long id);

	/**
	 * @param entity
	 * @return
	 * @author wonka
	 * @since 13/09/2012
	 */
	List<E> list(Class<E> entity);

	/**
	 * @param entity
	 * @param start
	 * @param maxResults
	 * @return
	 * @author wonka
	 * @since 13/09/2012
	 */
	List<E> list(Class<E> entity, Integer start, Integer maxResults);

	/**
	 * @param entity
	 * @param start
	 * @param maxResults
	 * @param orders
	 * @return Ordered results.
	 * @author wonka
	 * @since 22/09/2012
	 */
	List<E> list(Class<E> entity, Integer start, Integer maxResults, List<Order> orders);

	/**
	 * @param entity
	 * @param start
	 * @param maxResults
	 * @param orders
	 * @param conditions
	 * @return
	 * @author wonka
	 * @since 22/09/2012
	 */
	List<E> list(Class<E> entity, Integer start, Integer maxResults, List<Order> orders, Criterion... conditions);

	/**
	 * @param entityName
	 * @param entity
	 * @return And object E that matches the properties in entity.
	 * @author wonka
	 * @since 13/09/2012
	 */
	E retrieve(Class<E> entityName, E entity);

	/**
	 * @param entityName
	 * @param id
	 * @return And object E that matches the id.
	 * @author wonka
	 * @since 13/09/2012
	 */
	E retrieve(Class<E> entityName, long id);

	/**
	 * @param name
	 * @return The default implementation of Principal.
	 * @author wonka
	 * @since 29/03/2013
	 */
	User retrieveOwner(String name);

	/**
	 * @param entity
	 * @param id
	 * @return
	 * @author wonka
	 * @since 13/09/2012
	 */
	E update(Class<E> entity, long id);
}
