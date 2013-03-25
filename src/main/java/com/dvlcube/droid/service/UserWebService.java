package com.dvlcube.droid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.droid.bean.Role;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.dao.RoleDao;
import com.dvlcube.droid.dao.UserDao;
import com.dvlcube.droid.service.rr.NewUserRequest;
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
	private RoleDao roleDao;

	@Override
	protected UserDao getDao() {
		return dao;
	}

	@Override
	protected Class<User> getT() {
		return User.class;
	}

	@Override
	public Response<User> newUser(NewUserRequest userRequest) {
		User user = new User(userRequest);
		Response<User> response = add(user);
		roleDao.create(new Role(response.getContent()));
		return response;
	}
}
