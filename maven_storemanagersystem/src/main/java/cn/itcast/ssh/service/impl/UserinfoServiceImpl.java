package cn.itcast.ssh.service.impl;

import java.util.List;

import cn.itcast.ssh.dao.UserinfoDAO;
import cn.itcast.ssh.dao.impl.UserinfoDAOImpl;
import cn.itcast.ssh.domain.Userinfo;
import cn.itcast.ssh.service.UserinfoService;

public class UserinfoServiceImpl implements UserinfoService {

	// 注入UserinfoDAO
	private UserinfoDAO userinfoDAOImpl;

	public void setUserinfoDAOImpl(UserinfoDAOImpl userinfoDAOImpl) {
		this.userinfoDAOImpl = userinfoDAOImpl;
	}

	public List<Userinfo> findByNameQuery(String string, String name, String md5) {
		// TODO Auto-generated method stub
		return userinfoDAOImpl.findByNameQuery(string, name, md5);
	}

	public void add(Userinfo userinfo) {
		// TODO Auto-generated method stub
		userinfoDAOImpl.add(userinfo);
	}
}
