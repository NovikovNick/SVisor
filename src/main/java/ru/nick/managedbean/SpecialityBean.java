package ru.nick.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Speciality;

@Component("specialityBean")
@Scope("request")
public class SpecialityBean {

	@Inject
	@Named("specialityDao")
	SimpleCrudDao<Speciality> dao;
	
	private Long id;
	private String title;
	
	//If I don't use cache, I use lasy load, because JSF invoke this 4 times
	List<Speciality> allSpeciality;
	@PostConstruct
	private void refresh() {allSpeciality = dao.findAll();}
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;	}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	//*************        END      ********************//
	
	

	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		Speciality speciality = new Speciality();
		speciality.setId(getId());
		speciality.setTitle(getTitle());
		dao.add(speciality);
		clearForm();
		refresh();
	}
	/** Read */
	public List<Speciality> getAllSpeciality() { 
		return allSpeciality;
	}

	/** Update */
	public String update(Speciality speciality) {
		dao.update(speciality);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(Speciality speciality) {
		dao.delete(speciality);
		refresh();
		return null;
	}
	//*************        END      ********************//

	
	
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setId(0L);
		setTitle("");
	}
	//*************        END      ********************//
}
