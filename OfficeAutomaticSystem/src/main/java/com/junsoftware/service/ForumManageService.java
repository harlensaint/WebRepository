package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.Forum;

public interface ForumManageService {

	List<Forum> findAll();

	void save(Forum model);

	Forum findById(Long id);

	void delete(Forum model);

	void update(Forum forum);

	void moveUp(Forum model);

	void moveDown(Forum model);
}
