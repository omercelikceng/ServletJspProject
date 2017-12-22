package dao;

import java.util.List;

import model.User;

public interface IUserDAO {

	public void insertUser(User user);
	
	public List<User> getUsers();
	
	public void removeUser(int id);
	
	public void updateUser(User user);
}
