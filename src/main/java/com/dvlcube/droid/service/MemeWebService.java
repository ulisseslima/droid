package com.dvlcube.droid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.droid.bean.Meme;
import com.dvlcube.droid.dao.MemeDao;
import com.dvlcube.service.ServiceTemplate;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
@Service
public class MemeWebService extends ServiceTemplate<Meme> implements MemeService {

	@Autowired
	private MemeDao dao;

	@Override
	protected MemeDao getDao() {
		return dao;
	}

	@Override
	protected Class<Meme> getT() {
		return Meme.class;
	}
}
