package com.peopleyuqong.service.User;

import com.peopleyuqong.bean.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhuxt on 2016/11/15.
 */
public class UserServiceImplTest {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

	private static ClassPathXmlApplicationContext context = null;

	@BeforeClass
	public static void onlyOnce() {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testSave() throws Exception {
		IUserService userService = (IUserService) context.getBean("userService");
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
		IUserService userService = (IUserService) context.getBean("userService");
		User user = new User();
		user.setUsername("test");
		user.setProkey("test");
		user.setVisit(false);
		user.setFrequency(1000);
		user.setStart(new Date());
		user.setEnd(new Date());
		user.setTenancy(182);
		userService.update(user);

	}

	@Test
	public void testFind() throws Exception {
		IUserService userService = (IUserService) context.getBean("userService");
		User user = userService.find("test", "test");
		System.out.println(user.getUsername() + ":" + user.getProkey());
	}

	@Test
	public void testList() throws Exception {
		IUserService userService = (IUserService) context.getBean("userService");
		List<User> users = new ArrayList<>();
		users = userService.list();
		log.info("user 个数：" + users.size());
	}

	@Test
	public void testDelete() throws Exception {
		IUserService userService = (IUserService) context.getBean("userService");
		userService.delete("zhuxt", "test");
	}
}