package com.junsoftware.dao;

import java.util.List;

import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Reply;
import com.junsoftware.extra.IExtraDAO;
import com.junsoftware.utils.HqlUtils;

public interface ReplyDAO extends IExtraDAO<Reply> {

	List<Reply> findReplyListByTopicId(Long id);

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);
	
}
