package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.UserDAO;
import com.junsoftware.domain.User;
import com.junsoftware.extra.ExtraDAOImpl;
import com.junsoftware.utils.MD5Utlis;

@SuppressWarnings("all")
@Repository
public class UserDAOImpl extends ExtraDAOImpl<User> implements UserDAO {

	@Override
	public Boolean findLoginName(String loginName) {
		List<User> user = getHibernateTemplate().findByNamedQuery(
				"findLoginName", loginName);
		
		return user.isEmpty() ? false : true;
	}

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		List<User> users = getHibernateTemplate().findByNamedQuery("findByLoginNameAndPassword", loginName,MD5Utlis.md5(password));
		
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}

}
