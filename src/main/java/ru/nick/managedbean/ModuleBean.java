package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;

@Named("moduleBean")
public class ModuleBean {

	@Inject
	@Named("moduleDao")
	SimpleCrudDao<Module> dao;
	
	private String title;
	
	
	// ************* Getters/Setters ********************//
	// ************* START ********************//
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
		// ************* END ********************//

	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		Module module = new Module();
		module.setTitle(getTitle());
		
		module.setDate(getCurrentDate());//BO?
		
		dao.add(module);
		clearForm();
	}

	/** Read */
	public List<Module> getAllModules() {
		return dao.findAll();
	}
	/** Update */
	public Module update(Module module) {
		return dao.update(module);
	}
	/** Delete */
	public String delete(Module module) {
		dao.delete(module);
		return null;
	}

	// ************* END ********************//
 
	
	//BO?
	private java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	

	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setTitle("");
	}
	
	// ************* END ********************//

	
}
