package com.dvlcube.droid.dao;

import org.springframework.stereotype.Repository;

import com.dvlcube.dao.HibernateTemplate;
import com.dvlcube.droid.bean.Role;

/**
 * @author wonka
 * @since 24/03/2013
 */
@Repository
public class HibernateRoleDao extends HibernateTemplate<Role> implements RoleDao {

}
