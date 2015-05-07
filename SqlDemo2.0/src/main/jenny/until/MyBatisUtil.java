package main.jenny.until;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {

	/**
	 * 获取SqlSessionFactory
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory(String environmentId) {
		String resource = "config.xml";
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is,environmentId);
		return factory;
	}

	/**
	 * 获取SqlSession
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession(String environmentId) {
		return getSqlSessionFactory(environmentId).openSession();
	}

	/**
	 * 获取SqlSession
	 * @param isAutoCommit
	 *         true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
	 *         false 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession(String environmentId,boolean isAutoCommit) {
		return getSqlSessionFactory(environmentId).openSession(isAutoCommit);
	}

}
