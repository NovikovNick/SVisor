package ru.nick.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicTitle;

@Component("academicTitleBean")
@Scope("request")
public class AcademicTitleBean {

	@Inject
	@Named("academicTitleDao")
	SimpleCrudDao<AcademicTitle> dao;
	
	private String fullTitle;
	private String reducTitle;
	
	//If I don't use cache, I use lasy load, because JSF invoke this 4 times
	private List<AcademicTitle> allTitle;
	@PostConstruct
	private void refresh() {allTitle = dao.findAll();}
	
	//************* Getters/Setters ********************//
	public String getFullTitle() {return fullTitle;	}
	public void setFullTitle(String fullTitle) {this.fullTitle = fullTitle;	}

	public String getReducTitle() {	return reducTitle;	}
	public void setReducTitle(String reducTitle) {	this.reducTitle = reducTitle;	}
	//*************        END      ********************//
	
	
	
	

	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		AcademicTitle academicTitle = new AcademicTitle();
		academicTitle.setFullTitle(getFullTitle());
		academicTitle.setReducTitle(getReducTitle());
		dao.add(academicTitle);
		clearForm();
		refresh();
	}

	/** Read */
	public List<AcademicTitle> getAllTitle() {
		return allTitle;
	}

	/** Update */
	public String update(AcademicTitle academicTitle) {
		dao.update(academicTitle);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(AcademicTitle academicTitle) {
		dao.delete(academicTitle);
		refresh();
		return null;
	}
	// ************* END ********************//
		
	
	
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setFullTitle("");
		setReducTitle("");
	}
	// ************* END ********************//
	
}
