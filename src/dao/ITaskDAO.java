package dao;

import java.util.List;

import model.Task;
public interface ITaskDAO {

	public void inserTask(Task task);
	
	public List<Task> getTasks(String email);
	
	public void removeTask(int id);
	
	public void updateTask(Task task);
}
