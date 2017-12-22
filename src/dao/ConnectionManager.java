package dao;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionManager {
	
	private static volatile ConnectionManager conManInstance;
	private static Object mutex = new Object();
	
	private ConnectionManager() {
	}
	
	public static ConnectionManager getInstance() {
		ConnectionManager conManager = conManInstance;
		if(conManager== null) {
			synchronized (mutex) {
				conManager = conManInstance;
				if(conManInstance==null)
					conManInstance = conManager = new ConnectionManager();
			}
		}
		return conManager;
	}
	
	
	public DataSource getMysqlDataSource() {
		Properties prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream("dbConfig.properties");
		
		MysqlDataSource mysqlDataSource= null;
		try {
			prop.load(is);
			mysqlDataSource = new MysqlDataSource();
			mysqlDataSource.setUrl(prop.getProperty("url"));
			mysqlDataSource.setUser(prop.getProperty("username"));
			mysqlDataSource.setPassword(prop.getProperty("password"));
		} catch (Exception e) {
			System.out.println("Connection Manager : "+e.getMessage());
		}
		return mysqlDataSource;
		
	}
	
	
	

}
