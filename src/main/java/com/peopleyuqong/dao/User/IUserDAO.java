package com.peopleyuqong.dao.User;

import com.peopleyuqong.bean.User;

import java.util.List;

/**
 * Created by zhuxt on 2016/11/14.
 */
public interface IUserDAO {

	void insert(User user);

	void update(User user);

	void delete(int id);

	User find(int id);

	List<User> list();


}
