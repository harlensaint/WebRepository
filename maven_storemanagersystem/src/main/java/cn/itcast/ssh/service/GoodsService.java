package cn.itcast.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.ssh.domain.Goods;
import cn.itcast.ssh.exception.NoSuchAmountException;

public interface GoodsService {

	List<Goods> findBynm(String string, Object... values);

	List<Goods> findByname(String string, Object... values);

	void save(Goods goods);

	Goods findBynmOnly(String string, String nm);

	void out(Goods goods) throws NoSuchAmountException;

	List<Goods> findAllGoods(DetachedCriteria detachedCriteria);

}
