package cn.itcast.ssh.utils;

import org.apache.struts2.ServletActionContext;

import cn.itcast.ssh.domain.Userinfo;

public class WebManagerContext {
	public static Userinfo getLoginUserinfo() {
		Userinfo userinfo = (Userinfo) ServletActionContext.getRequest()
				.getSession().getAttribute("loginuser");

		return userinfo;
	}
}
