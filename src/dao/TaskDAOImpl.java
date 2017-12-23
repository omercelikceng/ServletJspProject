package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import model.Task;

public class TaskDAOImpl implements ITaskDAO {

	// private static final String SELECT_TASK = "select * from task where userid= ?";
	private static final String INSERT_TASK = "insert into task(useremail,taskdate,taskname,taskdescription) values (?,?,?,?)";
	private static final String ALL_TASKS = "select * from task where useremail=?";
	private static final String DELETE_TASK = "delete from task where id=?";
	private static final String UPDATE_TASK = "update task set taskname = ? , " + "taskDescription = ?";

	private DataSource getDataSource() {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		DataSource mysqlDataSource = connectionManager.getMysqlDataSource();
		return mysqlDataSource;
	}

	@Override
	public void inserTask(Task task) {
		DataSource dataSource = getDataSource();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_TASK)) {

			java.sql.Date sqlDate = new java.sql.Date(task.getTaskDate().getTime());
			ps.setString(1, task.getUserEmail());
			ps.setDate(2, sqlDate);
			ps.setString(3, task.getTaskName());
			ps.setString(4, task.getTaskDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDaoImpl : " + e.getMessage());
		}
	}

	@Override
	public List<Task> getTasks(String incomingEmail) {
		DataSource dataSource = getDataSource();
		List<Task> tasksList = new ArrayList<Task>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(ALL_TASKS)) {

			ps.setString(1, incomingEmail);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String useremail = resultSet.getString("useremail");
				String taskName = resultSet.getString("taskname");
				String taskDescription = resultSet.getString("taskdescription");
				Date taskDate = resultSet.getDate("taskdate");

				Task task = new Task(id, useremail, taskName, taskDescription, taskDate);
				tasksList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasksList;

	}

	@Override
	public void removeTask(int id) {
		DataSource dataSource = getDataSource();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_TASK)) {

			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateTask(Task task) {
		DataSource dataSource = getDataSource();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_TASK)) {

			pstmt.setString(1, task.getUserEmail());
			pstmt.setDate(2, (java.sql.Date) task.getTaskDate());
			pstmt.setString(3, task.getTaskName());
			pstmt.setString(4, task.getTaskDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
