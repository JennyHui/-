package main.jenny.PO.User;

import main.jenny.until.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by JennyHui on 2015/4/28
 */
public class UserDaoImpl implements UserDao {
	private final String findsql = "main.jenny.mapping.UserMapper.getUser";
	private final String insertsql = "main.jenny.mapping.UserMapper.assUser";
	private final String deletesql = "main.jenny.mapping.UserMapper.deleteUser";
	private final String updatesql = "main.jenny.mapping.UserMapper.updateUser";

	@Override
	public User insertUser(int id,String username,int age){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setAge(age);
		sqlSession.insert(insertsql,user);
		sqlSession.close();
		return user;
	}

	@Override
	public User deleteUserByUsername(String username){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		User user = new User();
		user.setUsername(username);
		sqlSession.delete(deletesql,user);
		sqlSession.close();
		return user;

	}

	@Override
	public User updateUser(int id,String newUsername,int newAge){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		User user = new User();
		user.setId(id);
		user.setUsername(newUsername);
		user.setAge(newAge);
		sqlSession.update(updatesql, user);
		sqlSession.close();
		return user;
	}

	@Override
	public User findUserByUsername(String username){
		// 从会话工厂中得到会话
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		// 通过sqlSession操作数据库
		// 第一个参数：user.xml定义的statement的id
		// 第二个参数：输入参数
		User user = sqlSession.selectOne(findsql,"JennyHui");
		//System.out.println(user);
		// 释放资源
		sqlSession.close();
		return user;
	}
}