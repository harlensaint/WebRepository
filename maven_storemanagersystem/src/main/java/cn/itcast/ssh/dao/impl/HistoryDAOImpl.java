package cn.itcast.ssh.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.components.Param;
import org.apache.taglibs.standard.tag.common.core.ParamSupport;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.ssh.dao.HistoryDAO;
import cn.itcast.ssh.domain.Goods;
import cn.itcast.ssh.domain.History;
import cn.itcast.ssh.pagination.PaginationBean;

public class HistoryDAOImpl extends HibernateDaoSupport implements HistoryDAO {

	public void save(History history) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(history);
	}

	// 利用QBC进行多条件组合分页查询
	@SuppressWarnings("all")
	public void paginationList(PaginationBean paginationBean) {
		// TODO Auto-generated method stub
		// 利用QBC进行多表的联合查询
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(History.class);
		DetachedCriteria goodsDetachedCriteria = detachedCriteria
				.createCriteria("goods");
		History history = (History) paginationBean.getObject();
		Goods goods = history.getGoods();
		if (goods != null) {
			if (StringUtils.isNotBlank(goods.getNm())) {
				goodsDetachedCriteria.add(Restrictions.eq("nm", goods.getNm()));
			}
			if (StringUtils.isNotBlank(goods.getName())) {
				goodsDetachedCriteria.add(Restrictions.like("name",
						goods.getName()));
			}
			if (!"null".equals(goods.getStore().getId())) {
				goodsDetachedCriteria.add(Restrictions.eq("store",
						goods.getStore()));
			}
		}

		// 先进行投影查询，查看所有的记录数
		detachedCriteria.setProjection(Projections.rowCount());

		long rowCount = (Long) getHibernateTemplate().findByCriteria(
				detachedCriteria).get(0);
		paginationBean.setTotalCount(rowCount);

		// 去除投影查询
		detachedCriteria.setProjection(null);

		// 进行分页查询
		// 修改结果集的封装策略
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

		int page = paginationBean.getPage();
		int pageSize = paginationBean.getPageSize();

		int firstResult = page * pageSize - pageSize;
		int maxResults = pageSize;
		List<History> histories = getHibernateTemplate().findByCriteria(
				detachedCriteria, firstResult, maxResults);
		paginationBean.setList(histories);

		// 总页数
		long totalPages = (rowCount + pageSize - 1) / pageSize;
		paginationBean.setTotalPage(totalPages);
	}

	@SuppressWarnings("all")
	public void paginationListBySql(PaginationBean paginationBean) {
		// 先拼接sql
		String sqlFrom = "from goods,history where goods.id=history.goodsid ";

		History history = (History) paginationBean.getObject();

		Goods goods = history.getGoods();
		List<Object> params = null;
		// 组合条件的拼接
		if (goods != null) {
			// 定义一个集合，用来接收参数
			params = new ArrayList();
			if (StringUtils.isNotBlank(goods.getNm())) {
				sqlFrom += "and goods.nm=? ";
				params.add(goods.getNm());
			}

			if (StringUtils.isNotBlank(goods.getName())) {
				sqlFrom += "and goods.name like ? ";
				params.add("%" + goods.getName() + "%");
			}

			if (!"null".equals(goods.getStore().getId())) {
				sqlFrom += "and goods.stroeid=? ";
				params.add(goods.getStore().getId());
			}
		}

		// 拼接select语句，查询总记录数
		String totalCountSql = "select count(1) " + sqlFrom;

		SQLQuery sqlTotalCountQuery = getSession()
				.createSQLQuery(totalCountSql);
		if (params != null) {

			for (int i = 0; i < params.size(); i++) {
				sqlTotalCountQuery.setParameter(i, params.get(i));
			}
		}

		BigDecimal totalCountBigDecimal = (BigDecimal) sqlTotalCountQuery
				.uniqueResult();
		// 获取满足条件的总记录数
		long totalCount = totalCountBigDecimal.longValue();
		// 封装总记录条数
		paginationBean.setTotalCount(totalCount);

		// 进行历史记录查询
		String historySql = "select history.* " + sqlFrom;

		SQLQuery historySqlQuery = getSession().createSQLQuery(historySql);

		// 封装参数
		if (params != null) {

			for (int i = 0; i < params.size(); i++) {
				historySqlQuery.setParameter(i, params.get(i));
			}
		}

		// 根据当前页和每页记录数，计算起始页数
		int page = paginationBean.getPage();
		int pageSize = paginationBean.getPageSize();
		// 计算起始页数
		int firstResult = page * pageSize - pageSize; // 这种计算时，数据记录是从0为第一条记录的
		int maxResult = pageSize;

		historySqlQuery.setFirstResult(firstResult);
		historySqlQuery.setMaxResults(maxResult);

		// 绑定结果集
		historySqlQuery.addEntity(History.class);

		List<History> histoies = historySqlQuery.list();

		// 将集合封装到paginationBean中
		paginationBean.setList(histoies);

		// 获取总页数
		long totalPages = (totalCount + pageSize - 1) / pageSize;

		// 封装总页数
		paginationBean.setTotalPage(totalPages);

	}
}
