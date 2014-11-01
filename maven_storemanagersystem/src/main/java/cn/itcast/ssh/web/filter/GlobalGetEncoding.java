package cn.itcast.ssh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.ssh.web.MyRequest;

/**
 * 利用过滤器实现全站的乱码问题 思路：1创建一个过滤器对象 2.将web容器创建的request对象包装起来
 * 3.将包装好的request和response转给其他组件
 * 
 * @author jun
 * 
 */
public class GlobalGetEncoding implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 将请求和相应转换成http协议的请求和相应
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 解决响应的乱码问题
		response.setHeader("contentType", "text/html");
		response.setCharacterEncoding("utf-8");
		// response.setContentType("text/html,charset=utf-8");
		// 解决post和get方式请求的乱码问题

		// 对应struts2已经解决了post方式的乱码问题，这里不再需要解决post方式的乱码
		/*
		 * if (request.getMethod().equalsIgnoreCase("post")) { // 如果是post方式请求
		 * request.setCharacterEncoding("utf-8"); chain.doFilter(request,
		 * response); return; }
		 */

		if (request.getMethod().equalsIgnoreCase("get")) {
			// 创建一个自定义的request
			MyRequest myrequest = new MyRequest(request);
			chain.doFilter(myrequest, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
