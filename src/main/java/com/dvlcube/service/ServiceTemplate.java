package com.dvlcube.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvlcube.bean.Trackable;
import com.dvlcube.dao.DaoCRUD;
import com.dvlcube.droid.bean.Owned;
import com.dvlcube.droid.bean.User;
import com.dvlcube.service.BasicInfo.Field;
import com.dvlcube.util.I18n;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class ServiceTemplate<T extends BasicInfo> implements AsyncCRUDService<T> {
	private static final int NO_PAGINATION = -1;

	@Autowired
	private Authentication authentication;

	private long lastModified = 0;

	public final Object lock = new Object();

	@Override
	public Response<T> add(final T entity) {
		setOwner(entity);
		final Serializable id = getDao().create(entity);
		entity.setId((Long) id);
		final Response<T> response = new Response<T>(true, entity);
		response.setMessage(I18n.Response.SUCCESS.key());
		return response;
	}

	@Override
	public Response<T> addOrUpdate(final T entity) {
		synchronized (lock) {
			try {
				final T match = get(entity).getContent();
				track(entity);
				if (match != null) {
					return update(entity);
				} else {
					return add(entity);
				}
			} finally {
				lock.notifyAll();
			}
		}
	}

	@Override
	public Response<T> delete(final long id) {
		getDao().delete(getT(), id);
		return new Response<>(true);
	}

	@Override
	public Response<T> delete(final T entity) {
		getDao().delete(entity);
		return new Response<>(true);
	}

	@Override
	public Response<T> get(final long id) {
		final T entity = getDao().retrieve(getT(), id);
		return new Response<T>(true, entity);
	}

	@Override
	public Response<T> get(final T t) {
		final T match = getDao().retrieve(getT(), t);
		return new Response<>(true, match);
	}

	/**
	 * @return The implementing DAO.
	 * @author wonka
	 * @since 16/09/2012
	 */
	protected abstract DaoCRUD<T> getDao();

	@Override
	public Object getLock() {
		return lock;
	}

	/**
	 * @return The implementing class type.
	 * @author wonka
	 * @since 16/09/2012
	 */
	protected abstract Class<T> getT();

	@Override
	public boolean hasUpdates(long date) {
		return date < lastModified;
	}

	@Override
	public Response<T> list() {
		final List<T> list = getDao().list(getT());
		return new Response<T>(true, list);
	}

	@Override
	public Response<T> list(final Criterion... restrictions) {
		return list(NO_PAGINATION, NO_PAGINATION, null, restrictions);
	}

	@Override
	public Response<T> list(final Integer start, final Integer maxResults) {
		return list(start, maxResults, null);
	}

	@Override
	public Response<T> list(final Integer start, final Integer maxResults, final List<Order> orders) {
		return list(start, maxResults, orders, new Criterion[0]);
	}

	@Override
	public Response<T> list(
		final Integer start,
		final Integer maxResults,
		final List<Order> orders,
		final Criterion... conditions) {
		final List<T> list = getDao().list(getT(), start, maxResults, orders, conditions);
		return new Response<T>(true, list);
	}

	@Override
	public Response<T> list(final List<Order> order, final Criterion... restrictions) {
		return list(NO_PAGINATION, NO_PAGINATION, order, restrictions);
	}

	@Override
	public Response<T> list(final Order... orders) {
		return list(NO_PAGINATION, NO_PAGINATION, Arrays.asList(orders), new Criterion[0]);
	}

	@Override
	public Response<T> listByDateModified(boolean desc) {
		String dateModified = Field.dateModified.name();
		if (desc) {
			return list(Order.desc(dateModified));
		} else {
			return list(Order.asc(dateModified));
		}
	}

	@Override
	public Response<T> listOld() {
		return listByDateModified(false);
	}

	@Override
	public Response<T> listRecent() {
		return listByDateModified(true);
	}

	/**
	 * @param entity
	 * @author wonka
	 * @since 29/03/2013
	 */
	private void setOwner(T entity) {
		if (authentication != null) {
			if (entity instanceof Owned) {
				User owner = getDao().retrieveOwner(authentication.getName());
				((Owned) entity).setOwner(owner);
			}
		}
	}

	/**
	 * "Tracks" an entity by updating its modified date.
	 * 
	 * @param entity
	 * @author wonka
	 * @since 12/03/2013
	 */
	private void track(Trackable entity) {
		Date date = new Date();
		entity.setDateModified(date);
		lastModified = date.getTime();
	}

	@Override
	public Response<T> update(final long id) {
		final T entity = getDao().update(getT(), id);
		final Response<T> response = new Response<T>(true, entity);
		response.setMessage(I18n.Response.SUCCESS.key());
		return response;
	}

	@Override
	public Response<T> update(final T entity) {
		if (entity.getId() == null) {
			final Response<T> response = new Response<T>(false, entity);
			response.setMessage(I18n.Response.FAIL.key());
			return response;
		} else {
			final Response<T> response = new Response<T>(true, getDao().update(entity));
			response.setMessage(I18n.Response.SUCCESS.key());
			return response;
		}
	}
}
