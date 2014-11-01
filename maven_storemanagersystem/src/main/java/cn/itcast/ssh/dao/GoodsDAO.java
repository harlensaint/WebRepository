package cn.itcast.ssh.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.ssh.domain.Goods;

public interface GoodsDAO {

	List<Goods> findBynm(String string, Object... values);

	List<Goods> findByname(String string, Object... values);

	void save(Goods goods);

	Goods load(String id);

	Goods findBynmOnly(String string, String nm);

	List<Goods> findAllGoods(DetachedCriteria detachedCriteria);

}
