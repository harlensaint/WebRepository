package cn.itcast.ssh.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.ssh.domain.Goods;
import cn.itcast.ssh.exception.NoSuchAmountException;
import cn.itcast.ssh.service.GoodsService;
import cn.itcast.ssh.service.impl.GoodsServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import flexjson.JSONSerializer;

public class GoodsAction extends ActionSupport implements ModelDriven<Goods> {

	private Goods goods = new Goods();
	private static final Logger logger = Logger.getLogger(GoodsAction.class);

	public Goods getModel() {
		// TODO Auto-generated method stub
		return goods;
	}

	private GoodsService goodsServiceImpl;

	public void setGoodsServiceImpl(GoodsServiceImpl goodsServiceImpl) {
		this.goodsServiceImpl = goodsServiceImpl;
	}

	public String findBynm() {

		List<Goods> goodsList = goodsServiceImpl.findBynm("Goods.findBynm", "%"
				+ goods.getNm() + "%");
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.include("id", "name", "nm", "amount", "label", "unit",
				"store.id").exclude("*.class", "store.*", "histories");
		String goodsString = jsonSerializer.deepSerialize(goodsList);
		// 不会进行深度转化
		ServletActionContext.getResponse().setContentType(
				"application/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(goodsString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getStackTrace());
		}
		return NONE;
	}

	public String findByname() {
		List<Goods> goodsList = goodsServiceImpl.findByname(
				"Goods.findByNameLike", "%" + goods.getName() + "%");
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.include("id", "name", "nm", "amount", "value", "unit",
				"store.id").exclude("*.class", "label", "store.*", "histories");
		String goodsString = jsonSerializer.deepSerialize(goodsList);
		// 进行深度转化
		ServletActionContext.getResponse().setContentType(
				"application/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(goodsString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getStackTrace());
		}
		return NONE;
	}

	public String save() {

		goodsServiceImpl.save(goods);

		return "savesuccess";
	}

	public String findBynmOnly() {

		Goods findGoods = goodsServiceImpl.findBynmOnly("Goods.findBynmOnly",
				goods.getNm());

		// 放入值栈中
		// ActionContext.getContext().put("root", findGoods);
		ActionContext.getContext().getValueStack().push(findGoods);

		return "findBynmOnly";
	}

	public String out() {

		try {
			goodsServiceImpl.out(goods);
			return "out";
		} catch (NoSuchAmountException e) {
			// TODO Auto-generated catch block
			logger.debug("出入货物大于库存货物量，出错了！");
			addActionError("库存不足");
			return INPUT;
		}
	}

	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Goods.class);
		if (!StringUtils.isBlank(goods.getName())) {
			detachedCriteria.add(Restrictions.like("name", goods.getName()));
		}

		if (!StringUtils.isBlank(goods.getNm())) {
			detachedCriteria.add(Restrictions.like("nm", goods.getNm()));
		}

		if ((goods.getStore() != null)
				&& (!"--请选择仓库--".equals(goods.getStore().getId()))) {
			detachedCriteria.add(Restrictions.eq("store", goods.getStore()));
		}

		List<Goods> goodsList = goodsServiceImpl.findAllGoods(detachedCriteria);

		// 压入栈顶
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("goodsList", goodsList);
		return "listsuccess";
	}

	public String historyList() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Goods.class);
		if (!StringUtils.isBlank(goods.getName())) {
			detachedCriteria.add(Restrictions.like("name", goods.getName()));
		}

		if (!StringUtils.isBlank(goods.getNm())) {
			detachedCriteria.add(Restrictions.like("nm", goods.getNm()));
		}

		if ((goods.getStore() != null)
				&& (!"null".equals(goods.getStore().getId()))) {
			detachedCriteria.add(Restrictions.eq("store", goods.getStore()));
		}

		List<Goods> goodsList = goodsServiceImpl.findAllGoods(detachedCriteria);

		// 压入栈顶
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("goodsList", goodsList);
		return "historyList";
	}
}
