package model;

public class User {

	private int id ;
	private String name;
	private String surname;
	private String eMail;
	private String password;
	private boolean admin=false;
	
	
	public User() {
		super();
	}
	
	public User(String name, String surname, String eMail, String password) {
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
		this.password = password;
	}

	public User(int id, String name, String surname, String eMail, String password, boolean admin) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
		this.password = password;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
}
