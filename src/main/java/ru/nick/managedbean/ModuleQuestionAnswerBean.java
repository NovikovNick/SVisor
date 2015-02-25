package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.ModuleQuestionAnswerBo;
import ru.nick.model.Answer;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Question.Difficult;

/**
 * Управление сразу 3 сущностями: модулем, вопросами и ответами.
 * 
 * @author NovikovNick
 *
 */
@Component("mqa")
@Scope("view")
public class ModuleQuestionAnswerBean {

	
	@Inject
	@Named("mqaBo")
	@Getter(AccessLevel.PROTECTED)
	private ModuleQuestionAnswerBo bo;
	
	@Getter @Setter
	private Module activeModule;
	
	public boolean active(){
		return activeModule != null;
	}
	
	public Difficult[] getQuestionDifficults(){
		return Difficult.values();
	}

	public void addModule() {
		bo.addModule();
	}
	public void addQuestion() {
		activeModule = bo.addQuestion(activeModule);
	}
	public void addAnswer(Question question) {
		activeModule =  bo.addAnswer(question);
	}

	
	public List<Module> getAllModules() {
		return bo.getAllModules();
	}

	public List<Question> getModuleQuestions() {
		
		return activeModule != null ? bo.getModuleQuestions(activeModule) : null;
	}

	public List<Answer> getQuestionAnswer(Question question) {
		return question != null ? bo.getQuestionAnswer(question) : null;
	}

	public String update(Module module) {
		activeModule = bo.update(module);
		return null;
	}

	public String update(Question question) {
		bo.update(question);
		return null;
	}

	public String update(Answer answer) {
		bo.update(answer);
		return null;
	}

	public String delete(Module module) {
		bo.delete(module);
		return null;
	}
	public String delete(Question question) {
		activeModule.getQuestions().remove(question);//Candidate to another layer
		bo.delete(question);
		return null;
	}
	public String delete(Answer answer) {
		answer.getOwnerQuestion().getAnswers().remove(answer);//Candidate to another layer
		bo.delete(answer);
		return null;
	}
	
	
}
