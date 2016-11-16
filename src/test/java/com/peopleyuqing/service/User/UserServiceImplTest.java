package com.peopleyuqing.service.User;

import com.peopleyuqing.bean.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhuxt on 2016/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Rollback
@Transactional
public class UserServiceImplTest {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

//	private static ClassPathXmlApplicationContext context = null;

	//	@BeforeClass
//	public static void onlyOnce() {
//		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	}
	@Autowired
	private IUserService userService;
	
	@Test
	public void testSave() throws Exception {
//		IUserService userService = (IUserService) context.getBean("userService");
		User user = new User();
		user.setUsername("zhuxt");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userService.save(user);
	}

	
	@Test
	public void testUpdate() throws Exception {
//		IUserService userService = (IUserService) context.getBean("userService");
		User user = new User();
		user.setUsername("update");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userService.save(user);
		User update = new User();
		update.setUsername("update");
		update.setProkey("test");
		update.setVisit(false);
		update.setFrequency(1000);
		update.setStart(new Date());
		update.setEnd(new Date());
		update.setTenancy(182);
		userService.update(update);

	}

	@Test
	public void testFind() throws Exception {
//		IUserService userService = (IUserService) context.getBean("userService");
		User user = new User();
		user.setUsername("find");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userService.save(user);
		User find = null;
		find = userService.find("find", "test");
		assertNotNull(find);
	}

	@Test
	public void testList() throws Exception {
//		IUserService userService = (IUserService) context.getBean("userService");
		User user = new User();
		user.setUsername("find");
		user.setProkey("test");
		user.setVisit(true);
		user.setFrequency(3000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(365);
		userService.save(user);
		List<User> users = new ArrayList<>();
		users = userService.list();
		assertEquals(users.size(), 1);
	}

	@Test
	public void testDelete() throws Exception {
//		IUserService userService = (IUserService) context.getBean("userService");
		userService.delete("zhuxt", "test");
	}
}