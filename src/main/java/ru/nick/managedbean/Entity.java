package ru.nick.managedbean;

import java.io.Serializable;

public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
	private String password;
	
	
	
	public Entity(String login, String password) {
		this.login = login;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
