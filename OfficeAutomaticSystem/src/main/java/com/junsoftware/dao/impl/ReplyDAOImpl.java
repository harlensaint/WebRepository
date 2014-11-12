package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.ReplyDAO;
import com.junsoftware.domain.Reply;
import com.junsoftware.extra.ExtraDAOImpl;

@Repository
@SuppressWarnings("all")
public class ReplyDAOImpl extends ExtraDAOImpl<Reply> implements ReplyDAO {

	@Override
	public List<Reply> findReplyListByTopicId(Long id) {
		List<Reply> list = getHibernateTemplate().findByNamedQuery(
				"findReplyListByTopicId", id);
		return list;
	}

}
