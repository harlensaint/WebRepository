package cn.itcast.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.ssh.dao.StoreDAO;
import cn.itcast.ssh.domain.Store;

public class StoreDAOImpl extends HibernateDaoSupport implements StoreDAO {

	public void add(Store store) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(store);
	}

	public List<Store> list() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Store.class);
	}

	public Store findById(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Store.class, id);
	}

	public void delete(Store store) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(store);
	}

	public void update(Store store) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(store);
	}
}
