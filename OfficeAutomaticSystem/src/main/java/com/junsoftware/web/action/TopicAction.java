package com.junsoftware.web.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Forum;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Reply;
import com.junsoftware.domain.Topic;
import com.junsoftware.extra.ExtraAction;
import com.junsoftware.utils.HqlUtils;

@Controller
@Scope("prototype")
public class TopicAction extends ExtraAction<Topic> {

	public String saveUI() {
		
		Forum forum = forumServiceImpl.findById(model.getForum().getId());
		
		set("forum", forum);
		
		return TOSAVEUI;
	}
	
	public String save(){
		
		//获取要创建的topic是属于哪个forum
		Forum forum = forumServiceImpl.findById(model.getForum().getId());
		
		model.setForum(forum);//让topic关联forum
		
		model.setAuthor(getAuthor());
		model.setContent(model.getContent());
		model.setDeleted(0);//默认的情况是未删除 0：代表未删除 1：代表删除
		model.setIp(getIp());
		model.setPostTime(new Date());
		model.setReplyCount(0L);
		model.setTitle(model.getTitle());
		model.setType(0);//0代表普通帖 1代表精华帖 2代表致顶贴
		
		//保存model
		topicServiceImpl.save(model);
		
		return "toShow";
	}
	
	public String show(){
		
		//获取当前的topic
		Topic topic=topicServiceImpl.findById(model.getId());
		push(topic);
		
		/*List<Reply> replies=replyServiceImpl.findReplyListByTopicId(model.getId());
		set("replies", replies);*/
		HqlUtils hql=new HqlUtils(Reply.class);
		hql.addWhereCondition("o.topic.id = ?", model.getId());
		PaginationBean paginationBean=replyServiceImpl.findPaginationBean(hql,currentPage);
		push(paginationBean);
		return "show";
	}

}
