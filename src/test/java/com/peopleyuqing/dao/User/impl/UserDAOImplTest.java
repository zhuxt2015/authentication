package com.peopleyuqing.dao.User.impl;

import com.peopleyuqing.bean.User;
import com.peopleyuqing.dao.User.IUserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhuxt on 2016/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback
public class UserDAOImplTest {

//	private static ClassPathXmlApplicationContext context = null;

	private static final Logger log = LoggerFactory.getLogger(UserDAOImplTest.class);

	/*@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext-test.xml");
	}*/

	@Autowired
	private IUserDAO userDAO;


	@Test
	public void testInsert() throws Exception {
		User user = new User();
		user.setUsername("insert");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userDAO.insert(user);
	}

	@Test
	public void testUpdate() throws Exception {

		User user = new User();
		user.setUsername("update");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(1000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(182);
		userDAO.insert(user);

		User update = new User();
		update.setUsername("update");
		update.setProkey("test");
		update.setVisit(false);
		update.setFrequency(2000);
		update.setStart(new Date());
		update.setEnd(new Date());
		update.setTenancy(182);
		userDAO.update(update);

	}


	@Test
	public void testFind() throws Exception {
		User user = new User();
		user.setUsername("find");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userDAO.insert(user);
		User find = new User();
		find = userDAO.find("find", "test");
		assertNotNull(find);
	}

	@Test
	public void testList() throws Exception {
		User user = new User();
		user.setUsername("list");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userDAO.insert(user);
		List<User> users = userDAO.list();
		assertEquals(users.size(), 1);
	}

	@Test
	public void testDelete() throws Exception {
		userDAO.delete("zhuxt", "test");
	}

}