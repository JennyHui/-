package main.jenny.PO.User;

/**
 * Created by JennyHui on 2015/4/27
 */
public class User {
	private int id;
	private int age;
	private int number;
	private String username;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getNum(){
		return number;
	}

	@Override
	public String toString(){
		String sqlString =  "id: "+this.id+" username: "+this.username+" age: "+this.age;
		return sqlString;
	}
}

