package com.peopleyuqing.dao.User;

import com.peopleyuqing.bean.User;

import java.util.List;

/**
 * Created by zhuxt on 2016/11/14.
 */
public interface IUserDAO {

	void insert(User user);

	void update(User user);

	void delete(String username, String prokey);

	User find(String username, String prokey);

	List<User> list();


}
