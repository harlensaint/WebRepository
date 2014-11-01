package cn.itcast.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.ssh.dao.GoodsDAO;
import cn.itcast.ssh.domain.Goods;

public class GoodsDAOImpl extends HibernateDaoSupport implements GoodsDAO {

	@SuppressWarnings("unchecked")
	public List<Goods> findBynm(String string, Object... values) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByNamedQuery(string, values);
	}

	@SuppressWarnings("unchecked")
	public List<Goods> findByname(String string, Object... values) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByNamedQuery(string, values);
	}

	public void save(Goods goods) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(goods);
	}

	public Goods load(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().load(Goods.class, id);
	}

	public Goods findBynmOnly(String string, String nm) {
		// TODO Auto-generated method stub
		List query = getHibernateTemplate().findByNamedQuery(string, nm);
		if (query.size() > 0) {
			Goods goods = (Goods) query.get(0);
			getHibernateTemplate().initialize(goods.getStore());
			return goods;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Goods> findAllGoods(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}
}
