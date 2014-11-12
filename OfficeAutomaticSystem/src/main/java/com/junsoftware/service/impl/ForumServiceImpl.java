package com.junsoftware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.ForumDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {

	@Autowired
	private ForumDAO forumDAOImpl;

	@Override
	public List<Forum> findAll() {

		return forumDAOImpl.findAll();
	}

	@Override
	public Forum findById(Long id) {
		return forumDAOImpl.findById(id);
	}

}
