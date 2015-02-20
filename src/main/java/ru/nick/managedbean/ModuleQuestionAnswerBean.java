package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.model.Answer;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Question.Difficult;

@Component("mqa")
@Scope("view")
public class ModuleQuestionAnswerBean {

	@Inject
	@Named("moduleBean")
	private ModuleBean module;

	@Inject
	@Named("questionBean")
	private QuestionBean question;
	
	@Inject
	@Named("answerBean")
	private AnswerBean answer;
	
	
	private Module activeModule;
	
	// ************* Getters/Setters ********************//
	// *************      START      ********************//
	public ModuleBean getModule() {
		return module;
	}

	public void setModule(ModuleBean module) {
		this.module = module;
	}

	public QuestionBean getQuestion() {
		return question;
	}

	public void setQuestion(QuestionBean question) {
		this.question = question;
	} 
	
	public AnswerBean getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerBean answer) {
		this.answer = answer;
	}
	
	public Module getActiveModule() {
		return activeModule;
	}

	public void setActiveModule(Module activeModule) {
		this.activeModule = activeModule;
	}

	// *************       END        ********************//
	
	
	
	public void addModule(){
		module.setTitle("Новый модуль");
		module.add();
	}
	public void addQuestion(){
		question.setContent("Новый вопрос");
		question.setDifficult(Difficult.MEDIUM);
		question.add(activeModule);
		
		activeModule = module.update(activeModule);
	}
	public boolean active(){
		return !(activeModule == null);
	}
	public String delete(Question question){
		
		if(active()){
			this.question.delete(question);
			activeModule = module.update(activeModule);
		}
		
		return null;
	}
	
	public void addAnswer(Question question){
		
		this.answer.setContent("Новый ответ");
		this.answer.setCorrect(false);		
		//this.answer.add(question);TODO:!!!
		
		activeModule = module.update(activeModule);
	}
	
	public String delete(Answer answer){
		
		this.answer.delete(answer);
		//this.question.update(question);
		activeModule = module.update(activeModule);
		
		return null;
	}
	
	public Difficult[] getQuestionDifficults(){
		return Difficult.values();
	}
}
