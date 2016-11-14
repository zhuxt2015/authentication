package com.peopleyuqong.dao.User;

import com.peopleyuqong.bean.User;
import com.peopleyuqong.dao.BaseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxt on 2016/11/14.
 */
public class UserDAO extends JdbcDaoSupport implements IUserDAO {


	@Override
	public void insert(User user) {
		String username = user.getUsername();
		String prokey = user.getProkey();
		int frequency = user.getFrequency();
		boolean visit = user.isVisit();
		Date start = user.getStart();
		int tenancy = user.getTenancy();
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, tenancy);
		Date end = c.getTime();

				String sql = "insert user(username,prokey,frequency,visit,start,end,tenancy) values(?,?,?,?,?,?,?)";
		Object[] params = new Object[]{username, prokey, frequency, visit, start, end, tenancy};
		getJdbcTemplate().update(sql,params);

	}

	@Override
	public void update(User user) {
		int id = user.getId();


	}

	@Override
	public void delete(int id) {

	}

	@Override
	public User find(int id) {
		return null;
	}

	@Override
	public List<User> list() {
		return null;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		context.start();
		User user = new User();
		user.setUsername("zhuxt");
		user.setProkey("test");
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		userDAO.insert(user);
	}
}
