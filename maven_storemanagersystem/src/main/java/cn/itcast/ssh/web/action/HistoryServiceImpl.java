package cn.itcast.ssh.web.action;

import cn.itcast.ssh.dao.HistoryDAO;
import cn.itcast.ssh.dao.impl.HistoryDAOImpl;
import cn.itcast.ssh.pagination.PaginationBean;
import cn.itcast.ssh.service.HistoryService;

public class HistoryServiceImpl implements HistoryService {

	private HistoryDAO historyDAOImpl;

	public void setHistoryDAOImpl(HistoryDAOImpl historyDAOImpl) {
		this.historyDAOImpl = historyDAOImpl;
	}

	public void paginationList(PaginationBean paginationBean) {
		// TODO Auto-generated method stub
		historyDAOImpl.paginationList(paginationBean);
	}

}
