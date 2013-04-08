package com.dvlcube.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dvlcube.bean.Identifiable;
import com.dvlcube.bean.QueryFieldName;
import com.dvlcube.droid.bean.User;
import com.dvlcube.util.CubeGenerics;

/**
 * 
 * @author wonka
 * @since 11/08/2012
 */
@Repository
public abstract class HibernateTemplate<E extends Identifiable> implements DaoCRUD<E> {
	public class CubeCriteria<T> {
		private final Criteria query;

		/**
		 * @param entity
		 * @param restrictions
		 * @author wonka
		 * @since 13/09/2012
		 */
		public CubeCriteria(final Class<?> entity, final Criterion... restrictions) {
			this(entity, null, null, null, restrictions);
		}

		/**
		 * @param entity
		 * @param start
		 * @param maxResults
		 * @param orders
		 * @param restrictions
		 * @author wonka
		 * @since 05/04/2013
		 */
		public CubeCriteria(final Class<?> entity, final Integer start, final Integer maxResults,
				final Set<Order> orders, final Criterion... restrictions) {
			this(entity, start, maxResults, orders, new HashSet<Criterion>(Arrays.asList(restrictions)), null);
		}

		/**
		 * @param entity
		 * @param start
		 * @param maxResults
		 * @param orders
		 * @param conditions
		 * @param aliases
		 * @author wonka
		 * @since 05/04/2013
		 */
		public CubeCriteria(Class<?> entity, Integer start, Integer maxResults, Set<Order> orders,
				Set<Criterion> conditions, Set<QueryFieldName> aliases) {
			query = getSession().createCriteria(entity);
			orderBy(orders);
			setPagination(start, maxResults);
			addConditions(conditions.toArray(new Criterion[conditions.size()]));
			addAliases(aliases);
		}

		/**
		 * @param aliases
		 * @author wonka
		 * @since 05/04/2013
		 */
		private void addAliases(Set<QueryFieldName> aliases) {
			for (QueryFieldName fieldName : aliases) {
				query.createAlias(fieldName.name(), fieldName.getAlias());
			}
		}

		/**
		 * @param restrictions
		 * @author wonka
		 * @since 22/09/2012
		 */
		private void addConditions(final Criterion... restrictions) {
			for (final Criterion criterion : restrictions) {
				if (criterion != null) {
					query.add(criterion);
				}
			}
		}

		public List<T> list() {
			return CubeGenerics.<T> uncheckedList(query.list());
		}

		/**
		 * @param orders
		 * @author wonka
		 * @since 22/09/2012
		 */
		private void orderBy(final Set<Order> orders) {
			if (orders == null) {
				query.addOrder(Order.desc("id"));
			} else {
				for (final Order by : orders) {
					if (by != null) {
						query.addOrder(by);
					}
				}
			}
		}

		/**
		 * @param start
		 * @param maxResults
		 * @author wonka
		 * @since 22/09/2012
		 */
		private void setPagination(final Integer start, final Integer maxResults) {
			if (start != null && start > -1) {
				query.setFirstResult(start);
			}
			if (maxResults != null && maxResults > 0) {
				query.setMaxResults(maxResults);
			}
		}
	}

	/**
	 * @param <T>
	 * @author wonka
	 * @since 05/03/2013
	 */
	public class CubeQuery<T> {
		private final Query query;

		public CubeQuery(final String string) {
			query = getSession().createQuery(string);
		}

		public List<T> list() {
			return CubeGenerics.<T> uncheckedList(query.list());
		}
	}

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Serializable create(final E entity) {
		final Serializable id = getSession().save(entity);
		return id;
	}

	/**
	 * @param object
	 * @return
	 * @author wonka
	 * @since 29/03/2013
	 */
	private Criteria criteriaByExample(Class<?> classname, Object object) {
		return getSession().createCriteria(object.getClass()).add(Example.create(object));
	}

	@Override
	public boolean delete(final Class<E> entity, final long id) {
		Object load = getSession().load(entity, id);
		final E object = CubeGenerics.<E> unchecked(load);
		if (null != object) {
			getSession().delete(object);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void delete(final E entity) {
		getSession().delete(entity);
	}

	/**
	 * @return hibernate session.
	 * @author wonka
	 * @since 11/08/2012
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<E> list(final Class<E> entity) {
		return new CubeCriteria<E>(entity).list();
	}

	@Override
	public List<E> list(final Class<E> entity, final Integer start, final Integer maxResults) {
		return new CubeCriteria<E>(entity, start, maxResults, null).list();
	}

	@Override
	public List<E> list(final Class<E> entity, final Integer start, final Integer maxResults, final List<Order> orders) {
		return list(entity, start, maxResults, orders);
	}

	@Override
	public List<E> list(
		final Class<E> entity,
		final Integer start,
		final Integer maxResults,
		final Set<Order> orders,
		final Criterion... conditions) {
		final List<E> results = new CubeCriteria<E>(entity, start, maxResults, orders, conditions).list();
		return results;
	}

	@Override
	public List<E> list(
		final Class<E> entity,
		final Integer start,
		final Integer maxResults,
		final Set<Order> orders,
		final Set<Criterion> conditions,
		final Set<QueryFieldName> aliases) {
		final List<E> results = new CubeCriteria<E>(entity, start, maxResults, orders, conditions, aliases).list();
		return results;
	}

	@Override
	public E retrieve(final Class<E> entityName, final E entity) {
		if (entity.getId() != null) {
			return retrieve(entityName, (Long) entity.getId());
		} else {
			Criteria criteria = criteriaByExample(entityName, entity);
			final List<E> matches = CubeGenerics.<E> uncheckedList(criteria.list());
			if (matches != null && !matches.isEmpty()) {
				return matches.get(0);
			} else {
				return null;
			}
		}
	}

	@Override
	public E retrieve(final Class<E> entity, final long id) {
		Object object = getSession().load(entity, id);
		E e = CubeGenerics.<E> unchecked(object);
		return e;
	}

	@Override
	public User retrieveOwner(String name) {
		Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.eq("name", name));
		final List<User> matches = CubeGenerics.<User> uncheckedList(criteria.list());
		if (matches != null && !matches.isEmpty()) {
			return matches.get(0);
		} else {
			throw new IllegalArgumentException("Couldn't find a registered user with name '" + name + "'");
		}
	}

	@Override
	public E update(final Class<E> entity, final long id) {
		Object object = getSession().load(entity, id);
		final E e = CubeGenerics.<E> unchecked(object);
		if (null != e) {
			Object updatedObject = getSession().merge(e);
			E updated = CubeGenerics.<E> unchecked(updatedObject);
			return updated;
		} else {
			return null;
		}
	}

	@Override
	public E update(final E entity) {
		Object object = getSession().merge(entity);
		final E updated = CubeGenerics.<E> unchecked(object);
		return updated;
	}
}
