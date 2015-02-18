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
public class AcademicDegreeBean extends AbstarctManagedBean<AcademicDegree> {

	@Inject
	@Named("academicDegreeDao")
	private SimpleCrudDao<AcademicDegree> dao;

	@FormField
	private String fullDegree;
	@FormField
	private String reducDegree;
	
	//If I don't use cache, I use lasy load, because JSF invoke this 4 times
	private List<AcademicDegree> allDegree;
	@Override 
	@PostConstruct
	protected void refresh() {allDegree = dao.findAll();}
	@Override
	protected Class<AcademicDegree> getGenericClass() {
		return AcademicDegree.class;
	}
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getFullDegree() {return fullDegree;}
	public void setFullDegree(String fullDegree) {this.fullDegree = fullDegree;}

	public String getReducDegree() {return reducDegree;}
	public void setReducDegree(String reducDegree) {this.reducDegree = reducDegree;}
	
	@Override
	protected SimpleCrudDao<AcademicDegree> getDao() {return dao;}
	//*************        END      ********************//
	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	
	/** Read */
	public List<AcademicDegree> getAllDegree() {
		return allDegree;
	}

	

	//*************        END      ********************//

	

}
