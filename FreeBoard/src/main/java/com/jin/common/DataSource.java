package com.jin.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// SqlSession 
public class DataSource {

	// 정적 메소드여야한다
	public static SqlSessionFactory getInstance() {
		// mybatis Config File 설정하는 경로 지정
		String resource = "config/mybatis-config.xml";

		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		return sqlSessionFactory;
	}
}
