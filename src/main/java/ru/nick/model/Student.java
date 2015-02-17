package ru.nick.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(name = "Student.getAll", query = "SELECT s from Student s"),
	@NamedQuery(name = "Student.getByLoginPassword", 
	query = "SELECT s from Student s WHERE s.login = ?1 AND s.password = ?2")
})
public class Student implements Identifiable{

	@Id
	private Long id;
	
	private String fstName;
	private String sndName;
	private String surname;
	
	private String login;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_group")
	private Group group;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getFstName() {return fstName;}
	public void setFstName(String fstName) {this.fstName = fstName;}

	public String getSndName() {return sndName;}
	public void setSndName(String sndName) {this.sndName = sndName;}

	public String getSurname() {return surname;}
	public void setSurname(String surname) {this.surname = surname;}
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public Group getGroup() {return group;}
	public void setGroup(Group group) {this.group = group;}
	
	
	
	
	@Override
	public boolean equals(Object obj) {

		if (null == obj) return false;
		if (this == obj) return true;
		if (!getClass().equals(obj.getClass())) return false;

		Student that = (Student) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}
