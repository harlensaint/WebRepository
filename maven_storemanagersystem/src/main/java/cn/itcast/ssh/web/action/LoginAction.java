package cn.itcast.ssh.web.action;

import java.util.List;

import cn.itcast.ssh.domain.Userinfo;
import cn.itcast.ssh.service.UserinfoService;
import cn.itcast.ssh.service.impl.UserinfoServiceImpl;
import cn.itcast.ssh.utils.MD5Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Userinfo> {

	private Userinfo userinfo = new Userinfo();

	public Userinfo getModel() {
		// TODO Auto-generated method stub
		return userinfo;
	}

	private UserinfoService userinfoServiceImpl;

	public void setUserinfoServiceImpl(UserinfoServiceImpl userinfoServiceImpl) {
		this.userinfoServiceImpl = userinfoServiceImpl;
	}

	public String login() {

		List<Userinfo> userinfos = userinfoServiceImpl.findByNameQuery(
				"Userinfo", userinfo.getName(),
				MD5Utils.md5(userinfo.getPassword()));
		// System.out.println(MD5Utils.md5(userinfo.getPassword()));
		if (userinfos.size() == 0) {
			// 说明用户不存在
			addFieldError("login.message", getText("login.message"));
			return INPUT;
		} else {
			// 说明用户存在，存入session中
			ActionContext.getContext().getSession()
					.put("loginuser", userinfos.get(0));
			return SUCCESS;
		}

	}
}
