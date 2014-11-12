package com.junsoftware.web.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Reply;
import com.junsoftware.domain.Topic;
import com.junsoftware.extra.ExtraAction;

@Controller
@Scope("prototype")
public class ReplyAction extends ExtraAction<Reply> {

	public String save() {
		// 获取此reply所属于的topic
		Topic topic = topicServiceImpl.findById(model.getTopic().getId());

		model.setAuthor(getAuthor());
		model.setContent(model.getContent());
		model.setCreateTime(new Date());
		model.setDeleted(0);
		model.setIp(getIp());
		model.setTopic(topic);

		replyServiceImpl.save(model);

		return "toShow";
	}

	public String saveUI() {

		// 获取当前要回复的reply所属的topic对象
		Topic topic = topicServiceImpl.findById(model.getTopic().getId());
		set("topic", topic);
		return TOSAVEUI;
	}

}
