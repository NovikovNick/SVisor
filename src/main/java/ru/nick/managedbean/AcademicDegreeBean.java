package ru.nick.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

@Component("academicDegreeBean")
@Scope("request")
public class AcademicDegreeBean {

	@Inject
	@Named("academicDegreeDao")
	SimpleCrudDao<AcademicDegree> dao;

	private String fullDegree;
	private String reducDegree;
	
	//If I don't use cache, I use lasy load, because JSF invoke this 4 times
	private List<AcademicDegree> allDegree;
	@PostConstruct
	private void refresh() {allDegree = dao.findAll();}
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getFullDegree() {return fullDegree;}
	public void setFullDegree(String fullDegree) {this.fullDegree = fullDegree;}

	public String getReducDegree() {return reducDegree;}
	public void setReducDegree(String reducDegree) {this.reducDegree = reducDegree;}
	//*************        END      ********************//

	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		AcademicDegree academicDegree = new AcademicDegree();
		academicDegree.setFullDegree(getFullDegree());
		academicDegree.setReducDegree(getReducDegree());
		dao.add(academicDegree);
		clearForm();
		refresh();
	}
	/** Read */
	public List<AcademicDegree> getAllDegree() {
		return allDegree;
	}

	/** Update */
	public String update(AcademicDegree academicDegree) {
		dao.update(academicDegree);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(AcademicDegree academicDegree) {
		dao.delete(academicDegree);
		refresh();
		return null;
	}
	//*************        END      ********************//

	
	
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setFullDegree("");
		setReducDegree("");
	}
	
	//*************        END      ********************//
}
