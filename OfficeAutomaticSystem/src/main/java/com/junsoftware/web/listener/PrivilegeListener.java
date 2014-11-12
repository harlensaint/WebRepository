package com.junsoftware.web.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.junsoftware.domain.Privilege;
import com.junsoftware.service.PrivilegeService;

public class PrivilegeListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//当servletcontext容器初始化时创建获取顶级权利
		//获取spring的IOC容器
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		PrivilegeService privilegeServiceImpl = (PrivilegeService) webApplicationContext.getBean("privilegeServiceImpl");
		
		List<Privilege> topPrivileges = privilegeServiceImpl.findTopPrivileges();
		
		//存入全局的域对象中
		event.getServletContext().setAttribute("topPrivileges", topPrivileges);

		//获取数据库中的所有的权利数组，存入到全局域中
		List<String> allPrivileges=privilegeServiceImpl.findAllPrivileges();
		
		event.getServletContext().setAttribute("allPrivileges", allPrivileges);
	}

}
