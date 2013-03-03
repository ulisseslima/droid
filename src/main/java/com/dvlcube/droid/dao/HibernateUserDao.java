package com.dvlcube.droid.dao;

import org.springframework.stereotype.Repository;

import com.dvlcube.dao.HibernateTemplate;
import com.dvlcube.droid.bean.User;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
@Repository
public class HibernateUserDao extends HibernateTemplate<User> implements UserDao {

}
