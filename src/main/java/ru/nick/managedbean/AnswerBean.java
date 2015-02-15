package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;
import ru.nick.model.Question;

@Named("answerBean")
public class AnswerBean {

	@Inject
	@Named("answerDao")
	SimpleCrudDao<Answer> dao;
	
	private String content;
	private boolean correct;
	
	
	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	public boolean isCorrect() {return correct;}
	public void setCorrect(boolean correct) {this.correct = correct;}
	//*************        END      ********************//
	
	
	
	
	
	// ************* User's CRUD methods ****************//
	// ************* START ********************//
	/** Create */
	public void add(Question quest) {
		Answer answer = new Answer();
		answer.setContent(getContent());
		answer.setCorrect(isCorrect());
		answer.setOwnerQuestion(quest);
		dao.add(answer);
		clearForm();
	}
	/** Read */
	public List<Question> getAllQuestions() {
		throw new UnsupportedOperationException();
	}
	/** Update */
	public String update(Answer answer) {
		dao.update(answer);
		return null;
	}

	/** Delete */
	public String delete(Answer answer) {
		dao.delete(answer);
		return null;
	}

	// *************        END      ********************//
			
			
			
	// *********** User's JSF-form methods **************//
	// ************* START ********************//
	private void clearForm() {
		setContent("");
		setCorrect(false);
	}
	//*************        END      ********************//
}
