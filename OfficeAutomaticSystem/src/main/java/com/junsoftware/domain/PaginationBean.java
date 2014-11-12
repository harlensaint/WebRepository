package com.junsoftware.domain;

import java.util.List;

/**
 * 用来封装页面的请求数据，和后台封装的响应数据，进行分页
 * 
 * @author harlensaint
 * 
 *         2014年11月11日,下午3:55:54
 */
public class PaginationBean {

	// 这两个变量时从页面传递过来的
	private int pageSize;// 每一页显示的数量，默认的显示的是每页10条记录
	private int currentPage = 1;// 当前请求的页数，默认的请求的是第一页

	// 这三个值是计算出的
	private int pageCount;// 总页数
	private int begin;// 分页条显示的起始页数
	private int end;// 分页条显示的结尾页数

	// 这两个值是从数据库中查询出的
	private long totalCount;// 总记录数
	private List recordList;// 要显示的记录集合

	public int getPageSize() {
		return pageSize;
	}

	public PaginationBean() {
		super();
	}

	public PaginationBean(int pageSize, int currentPage, long totalCount,
			List recordList) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.recordList = recordList;

		// 根据传递的参数计算出begin end pageCount

		this.pageCount = (int) ((totalCount + pageSize - 1) / pageSize);

		if (pageCount <= 10) {
			// 总记录数小于每页的显示的记录数
			this.begin = 1;
			this.end = pageCount;
		} else {
			// 总记录数大于每页显示的记录数
			this.begin = currentPage - 4;
			this.end = currentPage + 5;

			if (this.begin < 1) {
				this.begin = 1;
				this.end = this.begin + 9;
			}

			if (this.end > pageCount) {
				this.end = pageCount;
				this.begin = pageCount - 9;
			}
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

}
