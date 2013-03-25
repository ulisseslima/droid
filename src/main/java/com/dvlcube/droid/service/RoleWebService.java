package com.dvlcube.droid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.droid.bean.Role;
import com.dvlcube.droid.dao.RoleDao;
import com.dvlcube.service.ServiceTemplate;

/**
 * 
 * @author wonka
 * @since 24/03/2013
 */
@Service
public class RoleWebService extends ServiceTemplate<Role> implements RoleService {

	@Autowired
	private RoleDao dao;

	@Override
	protected RoleDao getDao() {
		return dao;
	}

	@Override
	protected Class<Role> getT() {
		return Role.class;
	}

}
