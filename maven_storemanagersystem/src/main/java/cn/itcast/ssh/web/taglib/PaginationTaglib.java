package cn.itcast.ssh.web.taglib;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PaginationTaglib extends SimpleTagSupport {

	JspContext jspContext;

	// 封装参数信息
	private int page;
	private int totalPage;
	private String paramsUrl;
	private String requestUrl;

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setParamsUrl(String paramsUrl) {
		this.paramsUrl = paramsUrl;
	}

	@Override
	// 当jsp页面执行了jsp自定的标签时，会执行doTag方法
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		// 准备模板数据
		Map<String, Object> datas = new HashMap<String, Object>();

		datas.put("page", page);
		datas.put("totalPage", totalPage);
		datas.put("paramsUrl", paramsUrl);

		PageContext pageContext = (PageContext) jspContext;
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String contextPath = request.getContextPath();
		datas.put("requestUrl", contextPath + requestUrl);

		// 计算begin和end
		int begin = page - 5; // 默认的begin
		int end = begin + 9;// 默认的end

		if (begin < 1) {
			begin = 1;
			end = begin + 9;
		}

		if (end > totalPage) {
			end = totalPage;
			begin = end - 9;
		}

		if (begin < 1) {
			begin = 1;
		}

		datas.put("begin", begin);
		datas.put("end", end);

		// 利用struts已经提供的freemarker的包，将freemarker的模板和数据组合起来
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(PaginationTaglib.class,
				"/template");

		Template template = configuration.getTemplate(
				"paginationFreemaker.ftl", "utf-8");

		try {
			template.process(datas, pageContext.getOut());
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected JspFragment getJspBody() {
		// TODO Auto-generated method stub
		return super.getJspBody();
	}

	@Override
	protected JspContext getJspContext() {
		// TODO Auto-generated method stub
		return super.getJspContext();
	}

	@Override
	public JspTag getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		// TODO Auto-generated method stub
		super.setJspBody(jspBody);
	}

	@Override
	public void setJspContext(JspContext pc) {
		// TODO Auto-generated method stub
		this.jspContext = pc;
	}

	@Override
	public void setParent(JspTag parent) {
		// TODO Auto-generated method stub
		super.setParent(parent);
	}

}
