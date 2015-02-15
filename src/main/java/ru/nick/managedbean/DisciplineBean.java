package ru.nick.managedbean;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.util.Messages;

@Component("disciplineBean")
@Scope("request")
public class DisciplineBean {
	
	@Inject
	@Named("disciplineDao")
	SimpleCrudDao<Discipline> dao;
	
	private String title;
	
		
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	//*************        END      ********************//
	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add() {
		Discipline discipline = new Discipline();
		discipline.setTitle(getTitle());
		dao.add(discipline);
		clearForm();
	}

	/** Read */
	public List<Discipline> findAllDisciplines() {
		return dao.findAll();
	}

	/** Update */
	public String update(Discipline discipline) {
		dao.update(discipline);
		return null;
	}

	/** Delete */
	public String delete(Discipline discipline) {
		dao.delete(discipline);
		return null;
	}
	// ************* END ********************//
		
	
	
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setTitle("");
	}

	public void validInputText(FacesContext context, UIComponent component, Object value) {

		String input = (String) value;

		if (input == "") {
			Messages.throwsValidateException("validation_empty", null);
		}
		if (input.length() < 2) {
			Messages.throwsValidateException("validation_length_small", null);
		}
		if (input.length() > 100) {
			Messages.throwsValidateException("validation_length_large", null);
		}

	}
	// ************* END ********************//
}
