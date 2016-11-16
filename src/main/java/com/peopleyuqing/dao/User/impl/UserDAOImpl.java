package com.peopleyuqing.dao.User.impl;

import com.peopleyuqing.bean.User;
import com.peopleyuqing.dao.User.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxt on 2016/11/14.
 */
//@Repository
@Component("userDAO")
public class UserDAOImpl extends JdbcDaoSupport implements IUserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);


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
		List<User> users = getJdbcTemplate().query(sql, params, new UserRowMapper());
		User user = null;
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
		//查询不到，报EmptyResultDataAccessException
//		return getJdbcTemplate().queryForObject(sql, params, new UserRowMapper());
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
		UserDAOImpl userDAOImpl = (UserDAOImpl) context.getBean("userDAO");
		userDAOImpl.insert(user);
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

	@Resource(name = "jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
}
