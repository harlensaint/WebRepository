package com.junsoftware.test;

import org.junit.Test;

import com.junsoftware.domain.Department;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.utils.HqlUtils;

public class PaginationTest {
	
	@Test
	public void test(){
		PaginationBean paginationBean = new PaginationBean(10, 15, 1000, null);
		System.out.println(paginationBean.getBegin());
		System.out.println(paginationBean.getEnd());
		System.out.println(paginationBean.getPageCount());
	}
	
	@Test
	public void test1(){
		HqlUtils hqlUtils=new HqlUtils(Department.class);
		
		hqlUtils.addWhereCondition("o.name=?","安全部");
		hqlUtils.addWhereCondition("o.age like ?","%李四%");
		
		hqlUtils.addOrderBy("o.id",HqlUtils.ASC);
		hqlUtils.addOrderBy("o.age", HqlUtils.DESC);
		
		System.out.println(hqlUtils.getCountHql());
		System.out.println(hqlUtils.getListHql());
		System.out.println(hqlUtils.getParams());
	}
	
}
