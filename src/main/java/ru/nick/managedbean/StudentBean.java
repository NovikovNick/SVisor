package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
import ru.nick.model.Student;

@Component("studentSBean")
@Scope("request")
public class StudentBean {
	
	@Inject
	@Named("studentDao")
	SimpleCrudDao<Student> dao;
	
	
	private long id;
	
	private String fstName;
	private String sndName;
	private String surname;
	
	private String login;
	private String password;
	
	private Group group;

	// *********** Getter/setter methods ****************//
	//*************       START      ********************//
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}

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
	//*************        END      ********************//
	
	
	// ************* User's CRUD methods ****************//
	//*************       START     ********************//
	/** Create */
	public String add() {
		Student student = new Student();
		
		student.setId(getId());
		
		student.setFstName(getFstName());
		student.setSndName(getSndName());
		student.setSurname(getSurname());
		
		student.setLogin(getLogin());
		student.setPassword(getPassword());
		student.setGroup(getGroup());
		dao.add(student);
		
		
		
		
		clearForm();
		
		return "logout";
	}

	/** Read */
	public List<Student> getAllStudent() {
		throw new UnsupportedOperationException();
	}

	/** Update */
	public String update(Student student) {
		dao.update(student);
		return null;
	}

	/** Update */
	public String delete(Student student) {
		dao.delete(student);
		return null;
	}
	//*************        END      ********************//
	
	
	
	
	
	// *********** User's JSF-form methods **************//
	// *************      START      ********************//
	private void clearForm() {
		setId(0L);
		setFstName("");
		setSndName("");
		setSurname("");
		setLogin("");
		setPassword("");
		setGroup(null);
	}
	// *************        END        ********************//
}
