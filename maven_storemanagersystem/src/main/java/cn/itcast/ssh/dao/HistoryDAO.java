package cn.itcast.ssh.dao;

import cn.itcast.ssh.domain.History;
import cn.itcast.ssh.pagination.PaginationBean;

public interface HistoryDAO {

	void save(History history);

	void paginationList(PaginationBean paginationBean);

}
