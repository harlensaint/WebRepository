package com.junsoftware.dao;

import com.junsoftware.domain.User;
import com.junsoftware.extra.IExtraDAO;

public interface UserDAO extends IExtraDAO<User>{

	Boolean findLoginName(String loginName);

	User findByLoginNameAndPassword(String loginName, String password);

}
