package com.dvlcube.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvlcube.dao.DaoCRUD;
import com.dvlcube.util.I18n;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class ServiceTemplate<T extends BasicBean> implements ServiceCRUD<T> {
	private static final int NO_PAGINATION = -1;

	@Override
	public Response<T> add(final T entity) {
		final Serializable id = getDao().create(entity);
		entity.setId((Long) id);
		final Response<T> response = new Response<T>(true, entity);
		response.setMessage(I18n.Response.SUCCESS.key());
		return response;
	}

	@Override
	public Response<T> addOrUpdate(final T entity) {
		final T match = get(entity).getContent();

		track(entity);

		if (match != null) {
			return update(entity);
		} else {
			return add(entity);
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

	/**
	 * @return The implementing class type.
	 * @author wonka
	 * @since 16/09/2012
	 */
	protected abstract Class<T> getT();

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

	/**
	 * "Tracks" an entity, updating its modified date.
	 * 
	 * @param entity
	 * @author wonka
	 * @since 12/03/2013
	 */
	private void track(final T entity) {
		entity.setDateModified(new Date());
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
