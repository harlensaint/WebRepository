package com.junsoftware.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Forum;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.domain.Topic;
import com.junsoftware.extra.ExtraAction;
import com.junsoftware.utils.HqlUtils;

@Controller
@Scope("prototype")
public class ForumAction extends ExtraAction<Forum> {

	
	// <select name="viewType">
	// <option value="0">全部主题</option>
	// <option value="1">全部精华贴</option>
	// </select>
	private int viewType;
	
	//	<select name="orderBy">
	//	<option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
	//	<option value="1">按最后更新时间排序</option>
	//	<option value="2">按主题发表时间排序</option>
	//	<option value="3">按回复数量排序</option>
	//</select>
	private int orderBy;
	// <select name="reverse">
	// <option value="true">降序</option>
	// <option value="false">升序</option>
	// </select>
	private boolean reverse;

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getViewType() {
		return viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public boolean getReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public String list() {
		// 获取所有的forum对象返回
		List<Forum> list = forumServiceImpl.findAll();
		set("forums", list);
		return LIST;
	}

	public String show() {

		// 获取到当前的forum
		Forum forum = forumServiceImpl.findById(model.getId());
		push(forum);

		// 查找所有的topic
	/*	List<Topic> topics = topicServiceImpl.findTopicListByForumId(model
				.getId());
		set("topics", topics);*/
		HqlUtils hql =new HqlUtils(Topic.class);
		
		if(viewType==1){
			//说明是查找全部精华帖
			hql.addWhereCondition("o.type = ?",1);
		}
		
		if(orderBy==0){
			//说明是按默认的顺序排序
			hql.addOrderBy("(case o.type when 2 then 2 else 0 end)",HqlUtils.DESC);
			hql.addOrderBy("o.postTime", HqlUtils.DESC);
		}else if(orderBy==1){
			//说明是按最后更新时间排序
			hql.addOrderBy("o.lastReplyTime",reverse?HqlUtils.DESC:HqlUtils.ASC);
		}else if(orderBy==2){
			//按主题发表时间排序
			hql.addOrderBy("o.postTime", reverse?HqlUtils.DESC:HqlUtils.ASC);
		}else if(orderBy==3){
			//按回复数量排序
			hql.addOrderBy("o.replyCount",reverse?HqlUtils.DESC:HqlUtils.ASC);
		}
		
		PaginationBean paginationBean=topicServiceImpl.findPaginationBean(hql,currentPage);
		push(paginationBean);
		return "show";
	}
}
