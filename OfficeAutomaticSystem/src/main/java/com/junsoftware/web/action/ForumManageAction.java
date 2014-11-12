package com.junsoftware.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.junsoftware.domain.Forum;
import com.junsoftware.extra.ExtraAction;
import com.junsoftware.service.impl.ForumManagerServiceImpl;

@SuppressWarnings("all")
@Controller
@Scope("prototype")
public class ForumManageAction extends ExtraAction<Forum> {

	public String list() {

		List<Forum> forums = forumManageServiceImpl.findAll();
		
		set("forums", forums);

		return LIST;
	}

	public String saveUI() {

		return TOSAVEUI;
	}

	public String save() {

		forumManageServiceImpl.save(model);

		return TOLIST;
	}

	public String delete() {

		// 先查找出要删除的model是否存在
		Forum forum = forumManageServiceImpl.findById(model.getId());
		if (forum != null) {
			// 说明存在，可以删除
			forumManageServiceImpl.delete(forum);
		}
		return TOLIST;
	}

	public String updateUI() {

		// 查找要修改的forum
		Forum forum = forumManageServiceImpl.findById(model.getId());
		if (forum != null) {
			// 说明存在，可以修改
			push(forum);
			return TOUPDATEUI;
		} else {
			// 说明此forum已被删除，提示
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter()
						.print("此论坛已被删除！<a href='http://localhost:8080/OfficeAutomaticSystem/forumManage_list.action'>返回</a>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return NONE;
		}
	}

	public String update() {

		// 先查找数据库中的forum
		Forum forum = forumManageServiceImpl.findById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumManageServiceImpl.update(forum);
		return TOLIST;
	}

	public String moveUp() {

		forumManageServiceImpl.moveUp(model);

		return TOLIST;
	}

	public String moveDown() {
		forumManageServiceImpl.moveDown(model);
		return TOLIST;
	}
}
