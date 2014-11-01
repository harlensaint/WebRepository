package cn.itcast.ssh.web.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.ssh.domain.History;
import cn.itcast.ssh.pagination.PaginationBean;
import cn.itcast.ssh.service.HistoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HistoryAction extends ActionSupport implements
		ModelDriven<History> {
	// 利用属性封装参数当前页和每一页的记录数
	private int page = 1;
	private int pageSize = 3;

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private History history = new History();

	public History getModel() {
		// TODO Auto-generated method stub
		return history;
	}

	// 注入HistoryServiceImpl
	private HistoryService historyServiceImpl;

	public void setHistoryServiceImpl(HistoryServiceImpl historyServiceImpl) {
		this.historyServiceImpl = historyServiceImpl;
	}

	public String paginationList() {
		PaginationBean paginationBean = new PaginationBean();
		paginationBean.setPage(page);
		paginationBean.setPageSize(pageSize);
		paginationBean.setObject(history);
		paginationBean.setParamsUrl(ServletActionContext.getRequest()
				.getQueryString());

		// 将数据传递给service层
		historyServiceImpl.paginationList(paginationBean);
		
		//将pagination压入栈顶
		ActionContext.getContext().getValueStack().set("paginationBean", paginationBean);

		return "paginationListSuccess";
	}

}
