package com.dvlcube.droid.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		List<User> contents = list(Restrictions.eq("name", username)).getContents();
		if (contents != null && contents.size() > 0) {
			User user = contents.get(0);
			return user;
		}
		return null;
	}

	@Override
	protected UserDao getDao() {
		return dao;
	}

	@Override
	public User getSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return getByUsername(auth.getName());
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
					Restrictions.in("element", listSiblings),//
					Restrictions.eq("active", true),//
					Restrictions.not(//
							Restrictions.eq("id", request.getUserId())));
		}
		return new Response<>(false);
	}

	@Override
	public void setOnlineStatus(String userName, boolean isOnline) {
		if (userName != null) {
			User user = getByUsername(userName);
			user.setOnline(isOnline);
			if (!isOnline) {
				user.setActive(false);
			}
			update(user).getContent();
		}
	}

	@Override
	public User updateLocation(UpdateLocationRequest request) {
		User user = new User(request.getUserId());
		user.setElement(request.getElement());
		user.setActive(request.isActive());
		return update(user).getContent();
	}
}
