package com.junsoftware.extra;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.junsoftware.domain.User;
import com.junsoftware.service.DepartmentService;
import com.junsoftware.service.ForumManageService;
import com.junsoftware.service.ForumService;
import com.junsoftware.service.PrivilegeService;
import com.junsoftware.service.ReplyService;
import com.junsoftware.service.RoleService;
import com.junsoftware.service.TopicService;
import com.junsoftware.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ExtraAction<T> extends ActionSupport implements ModelDriven<T> {

	// 定义静态的成员变量
	protected final static String LIST = "list";
	protected final static String TOLIST = "toList";
	protected final static String TOUPDATEUI = "toUpdateUI";
	protected final static String TOSAVEUI = "toSaveUI";
	
	protected int currentPage=1;//默认值是1
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected T model;

	@Override
	public T getModel() {
		return model;
	}

	public ExtraAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this
				.getClass().getGenericSuperclass();
		Type[] types = genericSuperclass.getActualTypeArguments();
		Class type = (Class) types[0];
		try {
			model = (T) type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected User getAuthor() {
		return (User) ServletActionContext.getRequest().getSession()
				.getAttribute("loginUser");
	}

	protected String getIp() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	// 抽取出push方法
	protected void push(Object o) {
		ServletActionContext.getContext().getValueStack().push(o);
	}

	// 抽取出set方法
	protected void set(String key, Object o) {
		ServletActionContext.getContext().getValueStack().set(key, o);
	}

	@Autowired
	protected RoleService roleServiceImpl;

	@Autowired
	protected DepartmentService departmentServiceImpl;

	@Autowired
	protected UserService userServiceImpl;

	@Autowired
	protected PrivilegeService privilegeServiceImpl;

	@Autowired
	protected ForumManageService forumManageServiceImpl;

	@Autowired
	protected ForumService forumServiceImpl;

	@Autowired
	protected TopicService topicServiceImpl;
	
	@Autowired
	protected ReplyService replyServiceImpl;
}
