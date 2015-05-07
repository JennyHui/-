package main.jenny.PO.User;

import org.apache.ibatis.annotations.Param;

/**
 * Created by JennyHui on 2015/4/28
 */
public interface UserDao {
	//�ƶ����ݿ���������???
	public User insertUser(@Param("id")int id,@Param("username")String username,@Param("age")int age);


	public User deleteUserByUsername(String username);
	public User updateUser(int id,String newUsername,int newAge);
	public User findUserByUsername(String username);
	public User findUserByUsername2(String name);
}
