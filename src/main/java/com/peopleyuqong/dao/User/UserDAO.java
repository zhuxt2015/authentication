package com.peopleyuqong.dao.User;

import com.peopleyuqong.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxt on 2016/11/14.
 */
public class UserDAO extends JdbcDaoSupport implements IUserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);


	@Override
	public void insert(User user) {
		String username = user.getUsername();
		String prokey = user.getProkey();
		int frequency = user.getFrequency();
		boolean visit = user.isVisit();
		Date start = user.getStart();
		Date end = user.getEnd();
		int tenancy = user.getTenancy();

		String sql = "insert user(username,prokey,frequency,visit,start,end,tenancy) values(?,?,?,?,?,?,?)";
		Object[] params = new Object[]{username, prokey, frequency, visit, start, end, tenancy};
		getJdbcTemplate().update(sql, params);

	}

	@Override
	public void update(User user) {
		String username = user.getUsername();
		String prokey = user.getProkey();
		int frequency = user.getFrequency();
		boolean visit = user.isVisit();
		Date start = user.getStart();
		Date end = user.getEnd();
		int tenancy = user.getTenancy();

		Object[] params = new Object[]{frequency, visit, start, end, tenancy,username, prokey};
		String sql = "update user set frequency=?, visit=?, start=?, end=?, tenancy=? where username=? and prokey=?";
		log.info(sql);

		getJdbcTemplate().update(sql, params);



	}

	@Override
	public void delete(String username, String prokey) {
		String sql = "delete from user where username=? and prokey=?";

		getJdbcTemplate().update(sql, new Object[]{username, prokey});

	}

	@Override
	public User find(String username, String prokey) {
		String sql = "select * from user where username=? and prokey=?";
		Object[] params = new Object[]{username, prokey};
		return getJdbcTemplate().queryForObject(sql, params, new UserRowMapper());
	}

	@Override
	public List<User> list() {
		String sql = "select * from user where 1=1";
		return getJdbcTemplate().query(sql, new Object[]{}, new UserRowMapper());

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

	class UserRowMapper implements RowMapper<User> {
		//实现ResultSet到User实体的转换
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User m = new User();
			m.setUsername(rs.getString("username"));
			m.setProkey(rs.getString("prokey"));
			m.setFrequency(rs.getInt("frequency"));
			m.setVisit(rs.getBoolean("visit"));
			m.setStart(rs.getDate("start"));
			m.setEnd(rs.getDate("end"));
			m.setTenancy(rs.getInt("tenancy"));
			return m;
		}
	}
}
