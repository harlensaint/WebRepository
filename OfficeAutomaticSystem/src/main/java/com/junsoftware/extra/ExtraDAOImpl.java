package com.junsoftware.extra;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.junsoftware.domain.PaginationBean;
import com.junsoftware.utils.HqlUtils;

@SuppressWarnings("all")
public class ExtraDAOImpl<T> extends HibernateDaoSupport implements
		IExtraDAO<T> {

	/**
	 * 用来接收具体的泛型的类型
	 */
	private Class entityClass;

	/*
	 * 这里通过购置方法来获取到泛型的具体类型， 当子类调用父类的构造函数时，会抓取到具体的泛型的类型
	 */
	public ExtraDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) this
				.getClass().getGenericSuperclass();
		Type[] types = genericSuperclass.getActualTypeArguments();
		Class type = (Class) types[0];
		entityClass = type;
	}

	//注入一个paginationBean,便于获取pageSize的值
	@Autowired
	protected PaginationBean paginationBean;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void add(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public T findById(Long id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 通用的分页查询的方法
	 * 
	 * @param hql
	 * @param currentPage
	 * @return
	 */
	public PaginationBean findPaginationBean(HqlUtils hql, int currentPage) {

		int pageSize =paginationBean.getPageSize();
		List params = hql.getParams();// 获取所有的查询参数集合
		Session session = getSession();

		String countHql = hql.getCountHql();// 获取查询总记录数的查询hql
		Query countQuery = session.createQuery(countHql);

		int index = 0;
		for (Object para : params) {
			countQuery.setParameter(index++, para);
		}

		long totalCount = (Long) countQuery.uniqueResult();// 获取总记录数

		String listHql = hql.getListHql();
		Query listQuery = session.createQuery(listHql);
		index = 0;// index 置零
		for (Object para : params) {
			listQuery.setParameter(index++, para);
		}

		// 设置查询的firstResult 和MaxResult

		int firstResult = currentPage * pageSize - pageSize;
		int maxResult = pageSize;
		listQuery.setFirstResult(firstResult);// 设置起始记录数
		listQuery.setMaxResults(maxResult);// 设置结束记录数
		List recordList = listQuery.list();

		PaginationBean paginationBean = new PaginationBean(pageSize,
				currentPage, totalCount, recordList);
		return paginationBean;
	}
}
