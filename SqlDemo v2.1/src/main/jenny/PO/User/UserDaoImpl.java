package main.jenny.PO.User;

import main.jenny.until.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by JennyHui on 2015/4/28
 */
public class UserDaoImpl implements UserDao {

	//匹配映射文件内对应的sql语句 - namespace.id
	private final String findsql = "UserMapper.getUser";
	private final String insertsql = "UserMapper.assUser";
	private final String deletesql = "UserMapper.deleteUser";
	private final String updatesql = "UserMapper.updateUser";
	private final String getUserSQL = "UserMapper.getUsers";
	private final String getUserNum = "UserMapper.getUserNum";


	//执行sql语句，sql语句返回值 --> 映射至java对象
	@Override
	public User findUserByUsername(String username){
		// 从会话工厂中得到会话
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		// 通过sqlSession操作数据库
		// 第一个参数：user.xml定义的statement的id
		// 第二个参数：输入参数
		User user = sqlSession.selectOne(findsql,username);
		//System.out.println(user);
		// 释放资源
		sqlSession.close();
		return user;
	}

	@Override
	public User insertUser(int id,String username,int age){
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setAge(age);
		sqlSession.insert(insertsql, user);
		sqlSession.close();
		return user;
	}

	@Override
	public User deleteUserByUsername(String username){
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		User user = new User();
		user.setUsername(username);
		sqlSession.delete(deletesql, user);
		sqlSession.close();
		return user;

	}

	@Override
	public User updateUser(int id,String newUsername,int newAge){
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		User user = new User();
		user.setId(id);
		user.setUsername(newUsername);
		user.setAge(newAge);
		sqlSession.update(updatesql, user);
		sqlSession.close();
		return user;
	}

	@Override
	public List<User> getUsers(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		List<User> user = sqlSession.selectList(getUserSQL);
		//System.out.println(user);
		// 释放资源
		sqlSession.close();
		return user;
	}

	@Override
	public User selectUserNumByUsername(String username){
		SqlSession sqlSession = MyBatisUtil.getSqlSession("DB1",true);
		User user = sqlSession.selectOne(getUserNum,username);
		sqlSession.close();
		return user;
	}
}