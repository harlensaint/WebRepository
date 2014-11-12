package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.TopicDAO;
import com.junsoftware.domain.Topic;
import com.junsoftware.extra.ExtraDAOImpl;

@Repository
@SuppressWarnings("all")
public class TopicDAOImpl extends ExtraDAOImpl<Topic> implements TopicDAO {

	@Override
	public List<Topic> findTopicListByForumId(Long id) {
		return getHibernateTemplate().findByNamedQuery(
				"findTopicListByForumId", id);
	}

}
