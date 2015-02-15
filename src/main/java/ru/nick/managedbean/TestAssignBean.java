package ru.nick.managedbean;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
import ru.nick.model.Teacher;
import ru.nick.model.Test;
import ru.nick.model.TestAssign;

@Component("assignBean")
@Scope("request")
public class TestAssignBean {
	
	@Inject
	@Named("assignDao")
	private SimpleCrudDao<TestAssign> dao;
	
	private String title;
	private String description;
	private Date date_start;//not use
	private Date date_end;//not use
	private int passing_score;
	private int completion_time;
	private int attempts;	
	
	private Teacher author;
	
	private Test test;
	
	private Group[] groups;
	
	private List<TestAssign> allTestAssigns;
	
	@PostConstruct
	private void refresh() {
		allTestAssigns = dao.findAll();
	}
	
	// ************* Getters/Setters ********************//
	// *************      START      ********************//	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public Date getDate_start() {return date_start;}
	public void setDate_start(Date date_start) {this.date_start = date_start;}
	
	public Date getDate_end() {return date_end;}
	public void setDate_end(Date date_end) {this.date_end = date_end;}
	
	public int getPassing_score() {return passing_score;}
	public void setPassing_score(int passing_score) {this.passing_score = passing_score;}
	
	public int getCompletion_time() {return completion_time;}
	public void setCompletion_time(int completion_time) {this.completion_time = completion_time;}
	
	public int getAttempts() {return attempts;}
	public void setAttempts(int attempts) {this.attempts = attempts;}
	
	public Teacher getAuthor() {return author;}
	public void setAuthor(Teacher author) {this.author = author;}
	
	public Test getTest() {return test;}
	public void setTest(Test test) {this.test = test;}
	
	public Group[] getGroups() {return groups;}
	public void setGroups(Group[] groups) {this.groups = groups;}
	
	//*************        END       ********************//
	
	
	// ************* User's CRUD methods ****************//
	//*************       START     ********************//
	/** Create */
	public void add() {
		TestAssign ta = new TestAssign();
		ta.setTitle(getTitle());
		ta.setDescription(getDescription());
		
		ta.setDate_start(getCurrentDate());
		ta.setDate_end(getCurrentDate());
		
		ta.setPassing_score(getPassing_score());
		ta.setCompletion_time(getCompletion_time());
		ta.setAttempts(getAttempts());
		ta.setAuthor(getAuthor());
		ta.setTest(getTest());
		ta.setGroups(new HashSet<Group>(Arrays.asList(getGroups())));
		
		dao.add(ta);
		clearForm();
		refresh();
	}
	/** Read */
	public List<TestAssign> getAllTestAssigns() {
		return allTestAssigns;
	}
	/** Update */
	public String update(TestAssign ta) {
		dao.update(ta);
		refresh();
		return null;
	}
	/** Update */
	public String delete(TestAssign ta) {
		dao.delete(ta);
		refresh();
		return null;
	}
	//*************        END      ********************//
	
	
	//BO?
	private java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	// *********** User's JSF-form methods **************//
	// *************      START      ********************//
	private void clearForm() {
		setAttempts(0);
		setAuthor(null);
		setCompletion_time(0);
		setDate_end(null);
		setDate_start(null);
		setDescription(null);
		setGroups(null);
		setPassing_score(0);
		setTest(null);
		setTitle(null);
	}
	//*************        END      ********************//
	
}
