package com.junsoftware.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个工具类是用来生成hql的工具类
 * 
 * @author harlensaint
 * 
 *         2014年11月11日,下午4:33:08
 */
public class HqlUtils {

	private String fromStr;// 用来拼接from部分
	private String whereConditionStr = "";// 用来拼接where部分
	private String orderByStr = "";// 用来拼接orderBy部分
	private List params = new ArrayList();// 用来存储参数信息

	public static final String ASC = " asc ";
	public static final String DESC = " desc ";

	/**
	 * 此构造函数是用来拼接select语句的
	 * 
	 * @param clazz
	 */
	public HqlUtils(Class clazz) {
		fromStr = "from " + clazz.getSimpleName() + " o ";// 拼接from部分，并给取一个别名o
	}

	/**
	 * 用来拼接where条件
	 * 
	 * @param condition
	 * @param values
	 */
	public void addWhereCondition(String condition, Object... values) {
		if (whereConditionStr.length() == 0) {
			// 说明是第一次拼接
			whereConditionStr = " where " + condition;
		} else {
			// 说明不是第一次拼接
			whereConditionStr += " and " + condition;
		}

		if (values != null && values.length > 0) {
			// 封装参数
			for (Object param : values) {
				params.add(param);
			}
		}
	}

	/**
	 * 用来拼接orderBy语句
	 * 
	 * @param orderBy
	 * @param orderCondition
	 */
	public void addOrderBy(String orderBy, String orderCondition) {
		if (orderByStr.length() == 0) {
			// 说明是第一次拼接
			orderByStr = " order by " + orderBy + orderCondition;
		} else {
			// 说明不是第一次拼接
			orderByStr += " , " + orderBy + orderCondition;
		}
	}

	/**
	 * 提供获取查询的拼接字符串的hql
	 * 
	 * @return
	 */
	public String getListHql() {
		return fromStr + whereConditionStr + orderByStr;
	}

	/**
	 * 提供查询总记录数的hql
	 * 
	 * @return
	 */
	public String getCountHql() {
		return "select count(*) " + fromStr + whereConditionStr;
	}

	/*
	 * 提供一个获取参数集合的方法
	 */
	public List getParams() {
		return params;
	}

}
