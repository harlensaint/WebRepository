package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.ForumDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.extra.ExtraDAOImpl;

@Repository
@SuppressWarnings("all")
public class ForumDAOImpl extends ExtraDAOImpl<Forum> implements ForumDAO{

	public List<Forum> findAll() {
		List<Forum> list = getHibernateTemplate().findByNamedQuery(
				"findAllByPosition");
		return list;
	}

}
