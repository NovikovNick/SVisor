package ru.nick.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
import ru.nick.model.Speciality;

@Component("groupBean")
@Scope("request")
public class GroupBean {

	@Inject
	@Named("groupDao")
	SimpleCrudDao<Group> dao;
	
	private String title;
	private int course;
	private Speciality speciality;
	//If I don't use cache, I use lasy load, because JSF invoke this 4 times
	private List<Group> allGroups;
	@PostConstruct
	private void refresh() {allGroups = dao.findAll();}
	
		
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getCourse() {return course;}
	public void setCourse(int course) {this.course = course;}
	
	public Speciality getSpeciality() {return speciality;}
	public void setSpeciality(Speciality speciality) {this.speciality = speciality;}
	//*************        END      ********************//
	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		Group group = new Group();
		group.setCourse(getCourse());
		
		group.setSpeciality(getSpeciality());
		group.setTitle(getTitle());
		
		dao.add(group);
		clearForm();
		refresh();
	}

	/** Read */
	public List<Group> getAllGroups() {
		return allGroups;
	}

	/** Update */
	public String update(Group group) {
		dao.update(group);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(Group group) {
		dao.delete(group);
		refresh();
		return null;
	}

	// *************        END      ********************//
		
		
		
		// *********** User's JSF-form methods **************//
		// ************* START ********************//
		private void clearForm() {
			setCourse(0);
			setSpeciality(null);
			setTitle("");
		}
		//*************        END      ********************//
}
