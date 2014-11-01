package cn.itcast.ssh.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.cache.annotation.CacheEvict;

import cn.itcast.ssh.dao.GoodsDAO;
import cn.itcast.ssh.dao.HistoryDAO;
import cn.itcast.ssh.dao.impl.HistoryDAOImpl;
import cn.itcast.ssh.domain.Goods;
import cn.itcast.ssh.domain.History;
import cn.itcast.ssh.exception.NoSuchAmountException;
import cn.itcast.ssh.service.GoodsService;
import cn.itcast.ssh.utils.WebManagerContext;

public class GoodsServiceImpl implements GoodsService {

	private HistoryDAO historyDAOImpl;

	public void setHistoryDAOImpl(HistoryDAOImpl historyDAOImpl) {
		this.historyDAOImpl = historyDAOImpl;
	}

	private GoodsDAO goodsDAOImpl;

	public void setGoodsDAOImpl(GoodsDAOImpl goodsDAOImpl) {
		this.goodsDAOImpl = goodsDAOImpl;
	}

	public List<Goods> findBynm(String string, Object... values) {
		// TODO Auto-generated method stub
		return goodsDAOImpl.findBynm(string, values);
	}

	public List<Goods> findByname(String string, Object... values) {
		// TODO Auto-generated method stub
		return goodsDAOImpl.findByname(string, values);
	}

	public void save(Goods goods) {
		// TODO Auto-generated method stub
		// 进行业务逻辑的判断
		if (StringUtils.isBlank(goods.getId())) {
			// 说明是新的货物数据，要保存到数据中去
			goodsDAOImpl.save(goods);// 内部关联游离态的对象store

			// 生成历史记录
			History history = new History();
			history.setGoods(goods);// 关联游离态的对象goods
			history.setAmount(goods.getAmount());
			history.setDatetime(new Date().toLocaleString());
			history.setOperatetype("1");
			history.setOperateruser(WebManagerContext.getLoginUserinfo()
					.getName());
			history.setRemain(goods.getAmount());

			historyDAOImpl.save(history);
		} else {
			// 说明不是新的货物记录，只改变货物库存数量
			Goods oldGoods = goodsDAOImpl.load(goods.getId());// goods由托管变为持久
			oldGoods.setAmount(oldGoods.getAmount() + goods.getAmount());// 此处利用快照自动更新，不用手动更新

			History history = new History();
			history.setGoods(oldGoods);// 关联持久态的对象goods
			history.setAmount(goods.getAmount());
			history.setDatetime(new Date().toLocaleString());
			history.setOperatetype("1");
			history.setOperateruser(WebManagerContext.getLoginUserinfo()
					.getName());
			history.setRemain(goods.getAmount() + oldGoods.getAmount());

			historyDAOImpl.save(history);
		}
	}

	public Goods findBynmOnly(String string, String nm) {
		// TODO Auto-generated method stub
		return goodsDAOImpl.findBynmOnly(string, nm);
	}

	public void out(Goods goods) throws NoSuchAmountException {
		// TODO Auto-generated method stub
		Goods goodsInStore = goodsDAOImpl.load(goods.getId());

		if (goodsInStore.getAmount() < goods.getAmount()) {
			// 说明货物不足
			throw new NoSuchAmountException();
		} else {
			// 说明货物充足
			goodsInStore
					.setAmount(goodsInStore.getAmount() - goods.getAmount());

			History history = new History();
			history.setGoods(goodsInStore);// 关联持久态的对象goodsInStore
			history.setAmount(goods.getAmount());
			history.setDatetime(new Date().toLocaleString());
			history.setOperatetype("2");
			history.setOperateruser(WebManagerContext.getLoginUserinfo()
					.getName());
			history.setRemain(goodsInStore.getAmount());

			historyDAOImpl.save(history);
		}
	}

	@CacheEvict(value = "storecache", allEntries = true)
	public List<Goods> findAllGoods(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return goodsDAOImpl.findAllGoods(detachedCriteria);
	}

}
