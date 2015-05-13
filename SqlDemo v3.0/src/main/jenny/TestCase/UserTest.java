package main.jenny.TestCase;

import main.jenny.PO.User.UserDao;
import main.jenny.PO.User.UserDaoImpl;
import org.testng.annotations.Test;

/**
 * Created by JennyHui on 2015/4/29
 */
public class UserTest {


	UserDao userDao = new UserDaoImpl();

	// 测试新增用户信息方法
	@Test
	public void testAddUser(){
		userDao.insertUser(18, "JackyCheng", 20 );
	}

	// 测试删除用户信息方法
	@Test
	public void testDeteleUserByUsername(){
		userDao.deleteUserByUsername("TestName");
	}

	// 测试更新用户信息方法
	@Test
	public void testUpdateUser(){
		userDao.updateUser(11, "AndyOn", 18);
	}

	// 测试查询用户信息方法
	@Test
	public void testFindUserByUsername() {
		System.out.println(userDao.findUserByUsername("JennyHui").getAge());
	}

	//sql查询结果映射到list中，脚本调用再自行处理
	@Test
	public void testgetUsers() {
		System.out.println(userDao.getUsers().get(1).toString());
	}

	//sql查询结果映射到list中，脚本调用再自行处理
	@Test
	public void testgetUserNum() {
		System.out.println(userDao.selectUserNumByUsername("JennyHui").getNum());
	}


}
