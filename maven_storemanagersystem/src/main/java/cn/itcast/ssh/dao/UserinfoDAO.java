package cn.itcast.ssh.dao;

import java.util.List;

import cn.itcast.ssh.domain.Userinfo;

public interface UserinfoDAO {

	List<Userinfo> findByNameQuery(String string, Object... valuse);

	void add(Userinfo userinfo);

}
