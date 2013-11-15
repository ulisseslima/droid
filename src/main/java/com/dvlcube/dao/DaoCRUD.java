package com.dvlcube.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.dvlcube.bean.Child;
import com.dvlcube.bean.Identifiable;
import com.dvlcube.bean.QueryFieldName;
import com.dvlcube.droid.bean.User;
import com.dvlcube.service.BasicInfo;

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
	List<E> list(Class<E> entity, Integer start, Integer maxResults, Set<Order> orders, Criterion... conditions);

	/**
	 * @param entity
	 * @param start
	 * @param maxResults
	 * @param orders
	 * @param conditions
	 * @param aliases
	 * @return
	 * @author wonka
	 * @since 05/04/2013
	 */
	List<E> list(
		Class<E> entity,
		Integer start,
		Integer maxResults,
		Set<Order> orders,
		Set<Criterion> conditions,
		Set<QueryFieldName> aliases);

	/**
	 * @param parent
	 * @param child
	 * @return a list of element ids.
	 * @author wonka
	 * @since 26/10/2013
	 */
	List<String> listSiblings(BasicInfo parent, Child<? extends BasicInfo> child);

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
	 * @param entity
	 * @param id
	 * @return an entity with the id.
	 * @author wonka
	 * @since 26/10/2013
	 */
	BasicInfo retrieveBasic(Class<?> entity, long id);

	/**
	 * @param entityName
	 * @param name
	 * @return the found object.
	 * @author wonka
	 * @since 15/11/2013
	 */
	E retrieveByName(Class<E> entityName, String name);

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
