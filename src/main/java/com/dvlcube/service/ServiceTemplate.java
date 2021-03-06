package com.dvlcube.service;

import static com.dvlcube.cuber.Cuber.$;
import static com.dvlcube.cuber.Cuber.sleep;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvlcube.bean.Child;
import com.dvlcube.bean.QueryFieldName;
import com.dvlcube.bean.Trackable;
import com.dvlcube.cuber.Debug;
import com.dvlcube.cuber.I18n;
import com.dvlcube.dao.DaoCRUD;
import com.dvlcube.droid.bean.Owned;
import com.dvlcube.droid.bean.Shared;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.rr.AsyncRequest;
import com.dvlcube.service.BasicInfo.Field;

/**
 * @author wonka
 * @since 15/09/2012
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class ServiceTemplate<T extends BasicInfo> implements AsyncCRUDService<T> {
	public static final String ENTITY_PACKAGE = "com.dvlcube.droid.bean";
	public static final Object lock = new Object();

	private static final int NO_PAGINATION = -1;

	@Autowired
	private Authentication authentication;
	private long lastModified = 0;

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

	public User getAuthenticatedUser() {
		if (authentication != null) {
			try {
				User user = getDao().retrieveOwner(((UserDetails) authentication.getPrincipal()).getUsername());
				return user;
			} catch (Exception e) {
				e.printStackTrace();
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
	public String getEntityPackage() {
		return ENTITY_PACKAGE;
	}

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

	/**
	 * @param asyncRequest
	 * @return the logged in user or the requested user.
	 * @author wonka
	 * @since 15/11/2013
	 */
	private User getUser(AsyncRequest asyncRequest) {
		User user = null;
		if (asyncRequest == null) {
			user = getAuthenticatedUser();
		} else {
			user = asyncRequest.getUser();
		}
		return user;
	}

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
	public Response<T> list(AsyncRequest asyncRequest, final Criterion... restrictions) {
		return list(asyncRequest, NO_PAGINATION, NO_PAGINATION, null, restrictions);
	}

	@Override
	public Response<T> list(AsyncRequest asyncRequest, final Integer start, final Integer maxResults) {
		return list(asyncRequest, start, maxResults, null);
	}

	@Override
	public Response<T> list(
		AsyncRequest asyncRequest,
		final Integer start,
		final Integer maxResults,
		final Set<Order> orders) {
		return list(asyncRequest, start, maxResults, orders, new Criterion[0]);
	}

	@Override
	public Response<T> list(
		AsyncRequest asyncRequest,
		final Integer start,
		final Integer maxResults,
		final Set<Order> orders,
		final Criterion... conditions) {
		User user = getUser(asyncRequest);
		Set<Criterion> authConditions = new HashSet<>();
		Set<QueryFieldName> aliases = new HashSet<>();
		if (user != null) {
			if (thisIsA(Shared.class)) {
				Criterion isSharedWithLoggedInUser = Restrictions.eq(Shared.Field.participants.join(), user.getId());
				aliases.add(Shared.Field.participants);
				authConditions.add(isSharedWithLoggedInUser);
			} else if (thisIsA(Child.class)) {
				Criterion isSharedWithLoggedInUser = Restrictions.eq(Child.Field.parent_participants.join(),
						user.getId());
				aliases.add(Child.Field.parent_participants);
				authConditions.add(isSharedWithLoggedInUser);
			} else if (thisIsA(Owned.class)) {
				Criterion isOwnedByLoggedInUser = Restrictions.eq(Owned.Field.owner.id(), user.getId());
				authConditions.add(isOwnedByLoggedInUser);
			}
		}
		Set<Criterion> allConditions = $(conditions).concatIntoSet(authConditions);
		final List<T> list = getDao().list(getT(), start, maxResults, orders, allConditions, aliases);
		Debug.log("Service Found %d users on same page", list.size());
		return new Response<T>(true, list);
	}

	@Override
	public Response<T> list(AsyncRequest asyncRequest, final Order... orders) {
		return list(asyncRequest, NO_PAGINATION, NO_PAGINATION, $(orders).asSet(), new Criterion[0]);
	}

	@Override
	public Response<T> list(AsyncRequest asyncRequest, final Set<Order> order, final Criterion... restrictions) {
		return list(asyncRequest, NO_PAGINATION, NO_PAGINATION, order, restrictions);
	}

	@Override
	public Response<T> list(final Criterion... restrictions) {
		return list(null, NO_PAGINATION, NO_PAGINATION, null, restrictions);
	}

	@Override
	public Response<T> listByDateModified(boolean desc) {
		String dateModified = Field.dateModified.name();
		if (desc) {
			return list(null, Order.desc(dateModified));
		} else {
			return list(null, Order.asc(dateModified));
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

	@Override
	public List<String> listSiblings(Child<? extends BasicInfo> child) {
		BasicInfo parent = child.getParent();
		return getDao().listSiblings(parent, child);
	}

	@Override
	public BasicInfo load(String entity_id) {
		String[] entity = entity_id.split("-");
		try {
			if (!entity[0].equalsIgnoreCase("nowhere")) {
				String entityName = getEntityPackage() + "." + $(entity[0]).capitalize();
				Class<?> entityClass = Class.forName(entityName);
				BasicInfo object = getDao().retrieveBasic(entityClass, $(entity[1]).l());
				return object;
			}
		} catch (Exception e) {
		}
		return null;
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
		Debug.log("last mod: %d", lastModified);
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

	@Override
	public void waitForUpdates(AsyncRequest request) {
		try {
			long tid = Thread.currentThread().getId();
			String dateString = new Date(request.getLastUpdate()).toString();
			int waits = 5;
			int tries = 0;
			Debug.log("Thread %d (%s) waiting...", tid, dateString);
			while (!hasUpdates(request.getLastUpdate()) && tries < waits) {
				tries++;
				sleep(1000);
			}
			Debug.log("Thread %d (%s) gave up.", tid, dateString);
		} catch (Exception e) {
		}
	}
}
