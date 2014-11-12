package com.junsoftware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.ForumManageDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.service.ForumManageService;

@Service
@Transactional
public class ForumManagerServiceImpl implements ForumManageService {

	@Autowired
	private ForumManageDAO forumManagerDAOImpl;

	@Override
	public List<Forum> findAll() {
		return forumManagerDAOImpl.findAll();
	}

	@Override
	public void save(Forum model) {
		// 保存Forum，并将position的值与id的值统一
		forumManagerDAOImpl.add(model);
		model.setPosition(Integer.parseInt(model.getId().toString()));
		model.setArticleCount(0L);
		model.setTopicCount(0);
	}

	@Override
	public Forum findById(Long id) {
		return forumManagerDAOImpl.findById(id);
	}

	@Override
	public void delete(Forum model) {
		forumManagerDAOImpl.delete(model);
	}

	@Override
	public void update(Forum forum) {
		forumManagerDAOImpl.update(forum);
	}

	@Override
	public void moveUp(Forum model) {
		forumManagerDAOImpl.moveUp(model);
	}

	@Override
	public void moveDown(Forum model) {
		forumManagerDAOImpl.moveDown(model);
	}
}
