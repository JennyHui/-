package main.jenny.PO.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JennyHui on 2015/4/28
 */
public interface UserDao {
	//制定数据库增加数据>>???
	public User insertUser(@Param("id")int id,@Param("username")String username,@Param("age")int age);


	public User deleteUserByUsername(String username);
	public User updateUser(int id,String newUsername,int newAge);
	public User findUserByUsername(String username);
	public List<User> getUsers();
	public User selectUserNumByUsername(String username);
}
