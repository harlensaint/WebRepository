package com.junsoftware.dao;

import java.util.List;

import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Topic;
import com.junsoftware.extra.IExtraDAO;
import com.junsoftware.utils.HqlUtils;

public interface TopicDAO extends IExtraDAO<Topic> {

	List<Topic> findTopicListByForumId(Long id);

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);
	
}
