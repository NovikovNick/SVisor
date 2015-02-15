package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Question.Difficult;

@Named("questionBean")
public class QuestionBean {

	@Inject
	@Named("questionDao")
	SimpleCrudDao<Question> dao;
	
	private String content;
	private Difficult difficult;
	
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public Difficult getDifficult() {return difficult;}
	public void setDifficult(Difficult difficult) {	this.difficult = difficult;	}
	//*************        END      ********************//
	
	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add(Module module) {
		
		Question quest = new Question();
		quest.setContent(getContent());
		quest.setDifficult(getDifficult());
		quest.setOwnerModule(module);
		dao.add(quest);
		clearForm();
	}
	/** Read */
	public List<Question> getAllQuestions() {
		throw new UnsupportedOperationException();
	}
	/** Update */
	public String update(Question question) {
		dao.update(question);
		return null;
	}

	/** Delete */
	public String delete(Question question) {
		dao.delete(question);
		return null;
	}

	// *************        END      ********************//
			
			
			
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setContent("");
		setDifficult(null);
	}
	public Difficult[] getDifficultItems(){
		 return Difficult.values();
	}
	//*************        END      ********************//
}
