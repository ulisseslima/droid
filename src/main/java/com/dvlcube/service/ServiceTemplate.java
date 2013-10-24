package com.dvlcube.service;

import static com.dvlcube.cuber.Cuber.$;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvlcube.bean.QueryFieldName;
import com.dvlcube.bean.Trackable;
import com.dvlcube.cuber.I18n;
import com.dvlcube.dao.DaoCRUD;
import com.dvlcube.droid.bean.Owned;
import com.dvlcube.droid.bean.Shared;
import com.dvlcube.droid.bean.User;
import com.dvlcube.service.BasicInfo.Field;

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

	private User getAuthenticatedUser() {
		if (authentication != null) {
			try {
				User user = getDao().retrieveOwner(authentication.getName());
				return user;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
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
	public Response<T> list(final Integer start, final Integer maxResults, final Set<Order> orders) {
		return list(start, maxResults, orders, new Criterion[0]);
	}

	@Override
	public Response<T> list(
		final Integer start,
		final Integer maxResults,
		final Set<Order> orders,
		final Criterion... conditions) {
		User user = getAuthenticatedUser();
		Set<Criterion> authConditions = new HashSet<>();
		Set<QueryFieldName> aliases = new HashSet<>();
		if (user != null) {
			if (thisIsA(Shared.class)) {
				Criterion isSharedWithLoggedInUser = Restrictions.eq(Shared.Field.participants.join(), user.getId());
				aliases.add(Shared.Field.participants);
				authConditions.add(isSharedWithLoggedInUser);
			} else if (thisIsA(Owned.class)) {
				Criterion isOwnedByLoggedInUser = Restrictions.eq(Owned.Field.owner.id(), user.getId());
				authConditions.add(isOwnedByLoggedInUser);
			}
		}
		Set<Criterion> allConditions = $(conditions).concatIntoSet(authConditions);
		final List<T> list = getDao().list(getT(), start, maxResults, orders, allConditions, aliases);
		return new Response<T>(true, list);
	}

	@Override
	public Response<T> list(final Order... orders) {
		return list(NO_PAGINATION, NO_PAGINATION, $(orders).asSet(), new Criterion[0]);
	}

	@Override
	public Response<T> list(final Set<Order> order, final Criterion... restrictions) {
		return list(NO_PAGINATION, NO_PAGINATION, order, restrictions);
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
	public Response<T> listOldFirst() {
		return listByDateModified(false);
	}

	@Override
	public Response<T> listRecentFirst() {
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
				try {
					User owner = getDao().retrieveOwner(authentication.getName());
					((Owned) entity).setOwner(owner);
					if (entity instanceof Shared) {
						HashSet<User> set = new HashSet<>();
						set.add(owner);
						((Shared) entity).setParticipants(set);
					}
				} catch (Exception e) {
					// TODO check why null pointer happens when trying to getName() from authentication even
					// if it's not null
				}
			}
		}
	}

	private boolean thisIsA(Class<?>... interfaceClasses) {
		return $(getT()).doesImplementAll(interfaceClasses);
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
			T saved = getDao().retrieve(getT(), (Long) entity.getId());
			$(entity).merge(saved);
			final Response<T> response = new Response<T>(true, getDao().update(saved));
			response.setMessage(I18n.Response.SUCCESS.key());
			return response;
		}
	}
}
