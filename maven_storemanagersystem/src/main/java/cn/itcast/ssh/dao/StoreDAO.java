package cn.itcast.ssh.dao;

import java.util.List;

import cn.itcast.ssh.domain.Store;

public interface StoreDAO {

	void add(Store store);

	List<Store> list();

	Store findById(String id);

	void delete(Store store);

	void update(Store store);

}
