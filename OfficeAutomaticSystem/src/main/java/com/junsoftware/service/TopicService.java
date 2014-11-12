package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Topic;
import com.junsoftware.utils.HqlUtils;

public interface TopicService {

	List<Topic> findTopicListByForumId(Long long1);

	void save(Topic model);

	Topic findById(Long id);

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);

}
