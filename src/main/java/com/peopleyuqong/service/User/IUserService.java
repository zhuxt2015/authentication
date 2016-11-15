package com.peopleyuqong.service.User;

import com.peopleyuqong.bean.User;

import java.util.List;

/**
 * Created by zhuxt on 2016/11/15.
 */
public interface IUserService {

	void save(User user);

	void update(User user);

	void delete(String username, String prokey);

	User find(String username, String prokey);

	List<User> list();
}
