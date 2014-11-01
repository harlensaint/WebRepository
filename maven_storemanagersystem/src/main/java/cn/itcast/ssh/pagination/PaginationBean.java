package cn.itcast.ssh.pagination;

import java.util.List;

/**
 * 
 * @author jun PaginationBean对象是用来封装分页查询的请求参数和相应参数，这里将请求参数和相应参数封装在一个Bean中
 */
public class PaginationBean<T> {
	
	//四个请求参数
	private String paramsUrl;
	private int page;
	private int pageSize;

	private T Object;
	
	//三个响应参数
	private List<T> list;
	private Long totalCount;
	private Long totalPage;

	public String getParamsUrl() {
		return paramsUrl;
	}

	public void setParamsUrl(String paramsUrl) {
		if (paramsUrl != null && paramsUrl.startsWith("page")) {
			int index = paramsUrl.indexOf("&");
			paramsUrl = paramsUrl.substring(index + 1);
		}
		this.paramsUrl = paramsUrl;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public T getObject() {
		return Object;
	}

	public void setObject(T object) {
		Object = object;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

}
