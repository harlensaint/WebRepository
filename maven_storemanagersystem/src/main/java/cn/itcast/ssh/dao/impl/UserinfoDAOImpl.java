package cn.itcast.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.ssh.dao.UserinfoDAO;
import cn.itcast.ssh.domain.Userinfo;

public class UserinfoDAOImpl extends HibernateDaoSupport implements UserinfoDAO {

	public List<Userinfo> findByNameQuery(String string, Object... valuse) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Userinfo> Userinfo = getHibernateTemplate().findByNamedQuery(
				string, valuse);
		return Userinfo;
	}

	public void add(Userinfo userinfo) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(userinfo);
	}

}
