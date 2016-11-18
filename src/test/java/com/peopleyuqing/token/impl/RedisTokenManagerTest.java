package com.peopleyuqing.token.impl;

import com.peopleyuqing.token.TokenManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by zhuxt on 2016/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@Rollback
public class RedisTokenManagerTest {

	@Autowired
	private TokenManager manager;

	@Test
	public void testCreateToken() throws Exception {
		String token = null;
		token = manager.createToken("test", "test");
		System.out.println(token + " len: " + token.length());
		assertNotNull(token);
	}

	@Test
	public void testCheckToken() throws Exception {

	}

	@Test
	public void testDecodeToken() throws Exception {

	}

	@Test
	public void testDeleteToken() throws Exception {

	}
}