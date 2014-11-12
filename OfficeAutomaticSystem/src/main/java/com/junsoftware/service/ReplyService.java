package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Reply;
import com.junsoftware.utils.HqlUtils;

public interface ReplyService {

	List<Reply> findReplyListByTopicId(Long id);

	void save(Reply model);

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);

}
