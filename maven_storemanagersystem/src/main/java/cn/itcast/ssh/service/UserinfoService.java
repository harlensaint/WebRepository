package cn.itcast.ssh.service;

import java.util.List;

import cn.itcast.ssh.domain.Userinfo;

public interface UserinfoService {

	List<Userinfo> findByNameQuery(String string, String name, String md5);

}
