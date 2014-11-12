package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.Forum;

public interface ForumService {

	List<Forum> findAll();

	Forum findById(Long id);
	
}
