package com.junsoftware.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junsoftware.dao.PrivilegeDAO;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DAOTest {
	
	@Autowired
	private PrivilegeDAO privilegeDAOImpl;
	
	@Test
	public void test(){
		List<String> list = privilegeDAOImpl.findAllPrivileges();
		System.out.println(list);
	}
}
