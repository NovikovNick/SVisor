package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.bo.ModuleQuestionAnswerBo;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Answer;
import ru.nick.model.Identifiable;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Question.Difficult;

@Named("mqaBo")
public class ModuleQuestionAnswerBoImpl implements ModuleQuestionAnswerBo{

	
	private final static String EMPTY_MODULE_TITLE = "модуль";
	private final static String EMPTY_QUESTION_CONTENT = "вопрос";
	private final static String EMPTY_ANSWER_CONTENT = "ответ";
	
	
	@Inject
	@Named("moduleBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Module> m;
	
	@Inject
	@Named("questionBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Question> q;
	
	@Inject
	@Named("answerBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Answer> a;
	
	@Override
	public void addModule() {
		Module module = new Module();
		module.setTitle(EMPTY_MODULE_TITLE);
		m.add(module);
	}

	@Override
	public Module addQuestion(Module module) {
		Question question = new Question();
		question.setContent(EMPTY_QUESTION_CONTENT);
		question.setDifficult(Difficult.MEDIUM);
		question.setOwnerModule(module);
		q.add(question);
		module.getQuestions().add(question);
		return m.update(module);
	}

	@Override
	public Module addAnswer(Question question) {
		Answer answer = new Answer();
		answer.setContent(EMPTY_ANSWER_CONTENT);
		answer.setCorrect(false);
		answer.setOwnerQuestion(question);
		a.add(answer);
		return m.update(question.getOwnerModule());
	}

	@Override
	public List<Module> getAllModules() {
		return m.findAll();
	}

	@Override
	public List<Question> getModuleQuestions(Module module) {
		return asOrderList(module.getQuestions());
	}

	@Override
	public List<Answer> getQuestionAnswer(Question question) {
		return asOrderList(question.getAnswers());
	}

	
	@Override
	public Module update(Module module) {
		return m.update(module);
	}

	@Override
	public Question update(Question question) {
		return q.update(question);
	}

	@Override
	public Answer update(Answer answer) {
		return a.update(answer);
	}

	@Override
	public void delete(Module module) {
		m.delete(module);
	}

	@Override
	public void delete(Question question) {
		q.delete(question);
	}

	@Override
	public void delete(Answer answer) {
		a.delete(answer);
	}

	
	
	
	private <T extends Identifiable> List<T> asOrderList(Set<T> set){
		if (set == null) {
			return new ArrayList<T>();
		}
		List<T> res = new ArrayList<T>(set);
		Collections.sort(res, new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return res;
	}
	
}
