package com.dvlcube.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dvlcube.bean.Identifiable;

/**
 * 
 * @author wonka
 * @since 11/08/2012
 */
@Repository
@SuppressWarnings("unchecked")
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

		public CubeCriteria(final Class<?> entity, final Integer start, final Integer maxResults,
				final List<Order> orders, final Criterion... restrictions) {
			query = getSession().createCriteria(entity);

			orderBy(orders);
			setPagination(start, maxResults);
			addConditions(restrictions);
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
			return query.list();
		}

		/**
		 * @param orders
		 * @author wonka
		 * @since 22/09/2012
		 */
		private void orderBy(final List<Order> orders) {
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

	public class CubeQuery<T> {
		private final Query query;

		public CubeQuery(final String string) {
			query = getSession().createQuery(string);
		}

		public List<T> list() {
			return query.list();
		}
	}

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Serializable create(final E entity) {
		final Serializable id = getSession().save(entity);
		return id;
	}

	@Override
	public boolean delete(final Class<E> entity, final long id) {
		final E object = (E) getSession().load(entity, id);
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
	public List<E> list() {
		throw new UnsupportedOperationException("not implemented yet");
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
	public List<E> list(
		final Class<E> entity,
		final Integer start,
		final Integer maxResults,
		final List<Order> orders) {
		return list(entity, start, maxResults, orders);
	}

	@Override
	public List<E> list(
		final Class<E> entity,
		final Integer start,
		final Integer maxResults,
		final List<Order> orders,
		final Criterion... conditions) {
		final List<E> results = new CubeCriteria<E>(entity, start, maxResults, orders, conditions).list();
		return results;
	}

	@Override
	public List<E> list(final Integer start, final Integer maxResults) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public E retrieve(final Class<E> entityName, final E entity) {
		if (entity.getId() != null) {
			return retrieve(entityName, (Long) entity.getId());
		} else {
			final List<E> list = getSession().createCriteria(entityName).add(Example.create(entity)).list();
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			} else {
				return null;
			}
		}
	}

	@Override
	public E retrieve(final Class<E> entity, final long id) {
		return (E) getSession().load(entity, id);
	}

	@Override
	public E retrieve(final long id) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public E update(final Class<E> entity, final long id) {
		final E object = (E) getSession().load(entity, id);
		if (null != object) {
			return (E) getSession().merge(object);
		} else {
			return null;
		}
	}

	@Override
	public E update(final E entity) {
		final E merge = (E) getSession().merge(entity);
		return merge;
	}
}
