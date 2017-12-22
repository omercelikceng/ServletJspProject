package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import model.User;

public class UserDAOImpl implements IUserDAO {

	private static final String SELECT_USER = "select * from user where email= ? and password=?";
	private static final String INSERT_USER = "insert into user(name,surname,email,password,admin) values (?,?,?,?,?)";
	private static final String ALL_USERS = "select * from user";
	private static final String DELETE_USER = "delete from user where id=?";
	private static final String UPDATE_USER = "update user set name = ? , "
			+ "surname = ? ,email = ? , password = ? where id = ?";


	private DataSource getDataSource() {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		DataSource mysqlDataSource = connectionManager.getMysqlDataSource();
		return mysqlDataSource;
	}

	@Override
	public void insertUser(User user) {
		DataSource dataSource = getDataSource();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.geteMail());
			ps.setString(4, user.getPassword());
			ps.setBoolean(5, user.isAdmin());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDaoImpl : " + e.getMessage());
		}

	}

	@Override
	public List<User> getUsers() {
		DataSource dataSource = getDataSource();
		List<User> usersList = new ArrayList<User>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(ALL_USERS)) {
			
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String eMail = resultSet.getString("email");
				String password = resultSet.getString("password");
				boolean isAdmin = resultSet.getBoolean("admin");

				User user = new User(id, name, surname, eMail, password, isAdmin);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public void removeUser(int id) {
		DataSource dataSource = getDataSource();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
			
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateUser(User user) {
		DataSource dataSource = getDataSource();
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER)){
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSurname());
			pstmt.setString(3, user.geteMail());
			pstmt.setString(4, user.getPassword());
			pstmt.setBoolean(5, user.isAdmin());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public User isPasswordValid(String eMail, String password) {
		DataSource dataSource = getDataSource();
		User user = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_USER)){
			pstmt.setString(1, eMail);
			pstmt.setString(2, password);
			
			ResultSet rs  = pstmt.executeQuery();
			while (rs.next()) {
                user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),
                		rs.getString("email"),rs.getString("password"),rs.getBoolean("admin"));
            }
		}catch (SQLException e) {
			System.out.println("ass"+e.getMessage());
		}
		return user;
	}
	
}
