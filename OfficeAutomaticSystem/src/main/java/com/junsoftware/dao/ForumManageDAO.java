package com.junsoftware.dao;

import com.junsoftware.domain.Forum;
import com.junsoftware.extra.IExtraDAO;

public interface ForumManageDAO extends IExtraDAO<Forum> {

	void moveUp(Forum model);

	void moveDown(Forum model);
	
}
