package com.junsoftware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.TopicDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Topic;
import com.junsoftware.service.TopicService;
import com.junsoftware.utils.HqlUtils;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDAO topicDAOImpl;

	@Override
	public List<Topic> findTopicListByForumId(Long id) {
		return topicDAOImpl.findTopicListByForumId(id);
	}

	@Override
	public void save(Topic topic) {
		Forum forum = topic.getForum();
		// 修改论坛的主题数量 和最后一次的发表的主题
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setLastTopic(topic);

		topicDAOImpl.add(topic);
	}

	@Override
	public Topic findById(Long id) {
		return topicDAOImpl.findById(id);
	}

	@Override
	public PaginationBean findPaginationBean(HqlUtils hql, int currentPage) {
		return topicDAOImpl.findPaginationBean(hql, currentPage);
	}

}
