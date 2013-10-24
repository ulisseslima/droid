package com.dvlcube.droid.dao;

import org.springframework.stereotype.Repository;

import com.dvlcube.dao.HibernateTemplate;
import com.dvlcube.droid.bean.Meme;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
@Repository
public class HibernateMemeDao extends HibernateTemplate<Meme> implements MemeDao {

}
