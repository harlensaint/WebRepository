package cn.itcast.ssh.service;

import java.util.List;

import cn.itcast.ssh.domain.Store;

public interface StoreService {

	void add(Store store);

	List<Store> list();

	Store findById(String id);

	void delete(Store isExist);

	void update(Store store);

}
