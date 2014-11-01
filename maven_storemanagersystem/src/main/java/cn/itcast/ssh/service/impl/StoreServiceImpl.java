package cn.itcast.ssh.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import cn.itcast.ssh.dao.StoreDAO;
import cn.itcast.ssh.dao.impl.StoreDAOImpl;
import cn.itcast.ssh.domain.Store;
import cn.itcast.ssh.service.StoreService;

public class StoreServiceImpl implements StoreService {

	private StoreDAO storeDAOImpl;

	public void setStoreDAOImpl(StoreDAOImpl storeDAOImpl) {
		this.storeDAOImpl = storeDAOImpl;
	}

	@CacheEvict(value = "storecache", allEntries = true)
	public void add(Store store) {
		// TODO Autogenerated method stub
		storeDAOImpl.add(store);
	}
	
	@Cacheable(value = "storecache")
	public List<Store> list() {
		// TODO Auto-generated method stub
		return storeDAOImpl.list();
	}

	public Store findById(String id) {
		// TODO Auto-generated method stub
		return storeDAOImpl.findById(id);
	}

	@CacheEvict(value = "storecache", allEntries = true)
	public void delete(Store store) {
		// TODO Auto-generated method stub
		Store isExist = findById(store.getId());
		if (isExist != null) {
			storeDAOImpl.delete(isExist);
		}
	}

	@CacheEvict(value = "storecache", allEntries = true)
	public void update(Store store) {
		// TODO Auto-generated method stub
		storeDAOImpl.update(store);
	}

}
