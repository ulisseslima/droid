package com.dvlcube.droid.dao;

import org.springframework.stereotype.Repository;

import com.dvlcube.dao.HibernateTemplate;
import com.dvlcube.droid.bean.Listing;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Repository
public class HibernateListingDao extends HibernateTemplate<Listing> implements ListingDao {

}
