package model;

import java.util.Date;

public class Task {
	private int id;
	private String userEmail;
	private String taskName;
	private String taskDescription;
	private Date taskDate;
	
	public Task(int id, String userEmail, String taskName, String taskDescription, Date taskDate) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskDate = taskDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", userEmail=" + userEmail + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", taskDate=" + taskDate + "]";
	}
	

}
