package cn.itcast.ssh.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.ssh.domain.Store;
import cn.itcast.ssh.service.StoreService;
import cn.itcast.ssh.service.impl.StoreServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import flexjson.JSONSerializer;

public class StoreAction extends ActionSupport implements ModelDriven<Store> {

	private Store store = new Store();

	public Store getModel() {
		// TODO Auto-generated method stub
		return store;
	}

	private StoreService storeServiceImpl;

	public void setstoreServiceImpl(StoreServiceImpl storeServiceImpl) {
		this.storeServiceImpl = storeServiceImpl;
	}

	public String add() {

		storeServiceImpl.add(store);

		return "addsuccess";
	}

	public String list() {

		List<Store> stores = storeServiceImpl.list();

		ActionContext.getContext().getValueStack().set("stores", stores);
		return "listsuccess";
	}

	public String delete() {

		storeServiceImpl.delete(store);

		return "addsuccess";
	}

	public Store findById() {
		return storeServiceImpl.findById(store.getId());
	}

	public String beforeUpdate() {
		Store isExist = findById();

		if (isExist != null) {
			// 压入值栈
			ActionContext.getContext().getValueStack().push(isExist);
			return "beforeUpdateSuccess";
		} else {
			addActionError("此仓库信息已被删除！");
			return INPUT;
		}

	}

	public String update() {
		storeServiceImpl.update(store);
		return "addsuccess";
	}

	public String findAllStore() {
		List<Store> stores = storeServiceImpl.list();
		// 放回json格式的数据
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.exclude("*.class");
		String serString = jsonSerializer.serialize(stores);
		ServletActionContext.getResponse().setContentType(
				"application/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(serString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}

}
