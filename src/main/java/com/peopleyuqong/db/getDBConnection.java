package com.peopleyuqong.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by zhuxt on 2016/11/11.
 */
public class getDBConnection {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Properties properties = new Properties()
		DruidDataSource dataSource = DruidDataSourceFactory.createDataSource()
		dataSource.getConnection();
	}
}
