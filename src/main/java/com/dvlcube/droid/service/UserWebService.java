package com.dvlcube.droid.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dvlcube.bean.Child;
import com.dvlcube.cuber.CubeGenerics;
import com.dvlcube.droid.bean.Role;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.dao.EventDao;
import com.dvlcube.droid.dao.RoleDao;
import com.dvlcube.droid.dao.UserDao;
import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.droid.service.rr.UpdateLocationRequest;
import com.dvlcube.service.BasicInfo;
import com.dvlcube.service.Response;
import com.dvlcube.service.ServiceTemplate;

/**
 * @author wonka
 * @since 10/09/2012
 */
@Service
public class UserWebService extends ServiceTemplate<User> implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private EventDao eventDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public Response<User> add(User entity) {
		return super.add(entity);
	}

	@Override
	public Response<User> addUser(NewUserRequest userRequest) {
		User existingUser = getByUsername(userRequest.getUsername());
		if (existingUser == null) {
			User user = new User(userRequest);
			Response<User> response = add(user);
			roleDao.create(new Role(response.getContent()));
			return response;
		} else {
			return new Response<>(false);
		}
	}

	public User getByUsername(String username) {
		User user = dao.retrieveByName(getT(), username);
		return user;
	}

	@Override
	protected UserDao getDao() {
		return dao;
	}

	@Override
	public User getSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			return getByUsername(userDetails.getUsername());
		}
		return null;
	}

	@Override
	public Long getSessionId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			User user = getByUsername(userDetails.getUsername());
			if (user != null) {
				return user.getId();
			}
		}
		return null;
	}

	@Override
	protected Class<User> getT() {
		return User.class;
	}

	@Override
	public Response<User> listUsersOnSamePage(UpdateLocationRequest request) {
		BasicInfo subject = load(request.getElement());
		if (subject != null && subject instanceof Child) {
			List<String> listSiblings = listSiblings(CubeGenerics.<Child<? extends BasicInfo>> unchecked(subject));
			return list(//
					request,//
					Restrictions.in("element", listSiblings),//
					Restrictions.eq("online", true), Restrictions.not(//
							Restrictions.eq("id", request.getUserId())));
		}
		return new Response<>(false);
	}

	@Override
	public void setOnlineStatus(boolean isOnline) {
		Long userId = getSessionId();
		if (userId != null) {
			User user = new User(userId);
			user.setOnline(isOnline);
			if (!isOnline) {
				user.setActive(false);
			}
			update(user).getContent();
		}
	}

	@Override
	public User updateLocation(UpdateLocationRequest request) {
		User saved = get(request.getUserId()).getContent();

		if (saved.getElement().equals(request.getElement()) && request.isActive() == saved.getActive()) {
			return saved;
		}

		User user = new User(request.getUserId());
		user.setElement(request.getElement());
		user.setActive(request.isActive());
		user.setOnline(true);
		return addOrUpdate(user).getContent();
	}
}
