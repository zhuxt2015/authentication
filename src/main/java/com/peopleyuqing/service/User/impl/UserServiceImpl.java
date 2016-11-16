package com.peopleyuqing.service.User.impl;

import com.peopleyuqing.bean.User;
import com.peopleyuqing.dao.User.IUserDAO;
import com.peopleyuqing.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxt on 2016/11/15.
 */
@Component("userService")
//@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	/*private void init() {
		System.out.println(" UserServiceImpl init-------------");
		this.userDAO = new UserDAOImpl();
	}*/

	public UserServiceImpl() {
		System.out.println("UserServiceImpl constructure------------------");
	}

	@Override
	public void save(User user) {
		int tenancy = user.getTenancy();
		Date start = user.getStart();
		//结束时间 = 开始时间 + 租期
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, tenancy);
		Date end = c.getTime();
		user.setEnd(end);
		userDAO.insert(user);
	}


	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(String username, String prokey) {
		userDAO.delete(username, prokey);
	}

	/**
	 * 查询单个User
	 * 不使用事务管理，不允许更新
	 * @param username
	 * @param prokey
	 * @return
	 */
//	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public User find(String username, String prokey) {
		return userDAO.find(username, prokey);
	}

	/**
	 * 查询所有User
	 * 不使用事务管理，不允许更新
	 * @return
	 */
//	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<User> list() {
		return userDAO.list();
	}

	/*public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Autowired
	public void setUserDAO(IUserDAO userDAO) {
		System.out.println("set userdao----------------------");
		this.userDAO = userDAO;
		System.out.println(this.userDAO);
	}*/

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		context.start();
		IUserDAO userDAO = (IUserDAO) context.getBean("userDAOImpl");
		System.out.println(userDAO.toString());
	}
}
