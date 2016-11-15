package com.peopleyuqong.dao.User;

import com.peopleyuqong.bean.User;
import org.springframework.stereotype.Component;

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
