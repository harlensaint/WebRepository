package com.junsoftware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.ReplyDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Reply;
import com.junsoftware.domain.Topic;
import com.junsoftware.service.ReplyService;
import com.junsoftware.utils.HqlUtils;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAOImpl;
	
	@Override
	public List<Reply> findReplyListByTopicId(Long id) {
		
		return replyDAOImpl.findReplyListByTopicId(id);
	}

	@Override
	public void save(Reply model) {
		replyDAOImpl.add(model);
		
		//修改topic中的相关信息
		Topic topic = model.getTopic();
		topic.setLastReply(model);
		topic.setLastReplyTime(model.getCreateTime());
		topic.setReplyCount(topic.getReplyCount()+1);
		
		//修改forum中的相关信息
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount()+1);
	}

	@Override
	public PaginationBean findPaginationBean(HqlUtils hql, int currentPage) {
		return replyDAOImpl.findPaginationBean(hql,currentPage);
	}

}
