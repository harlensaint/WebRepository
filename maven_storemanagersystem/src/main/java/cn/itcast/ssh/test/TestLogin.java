package cn.itcast.ssh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.ssh.domain.Userinfo;
import cn.itcast.ssh.service.impl.UserinfoServiceImpl;
import cn.itcast.ssh.utils.MD5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestLogin {
	@Autowired
	private UserinfoServiceImpl userinfoServiceImpl;

	@Test
	public void testAdd() {
		Userinfo userinfo = new Userinfo();
		userinfo.setId("1");
		userinfo.setName("admin");
		userinfo.setPassword(MD5Utils.md5("admin"));

		userinfoServiceImpl.add(userinfo);
	}
}
