package com.peopleyuqong.service.User;

import com.peopleyuqong.bean.User;
import com.peopleyuqong.dao.User.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxt on 2016/11/15.
 */
@Transactional
public class UserServiceImpl implements IUserService {

	private IUserDAO userDAO;


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
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public User find(String username, String prokey) {
		return userDAO.find(username, prokey);
	}

	/**
	 * 查询所有User
	 * 不使用事务管理，不允许更新
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<User> list() {
		return userDAO.list();
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
