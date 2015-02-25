package ru.nick.managedbean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.TestBo;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Test;

@Component("testBean")
@Scope("view")
public class TestBean extends AbstarctManagedBean<Test> {

	@Inject
	@Named("testBo")
	@Getter(AccessLevel.PROTECTED)
	private TestBo bo;

	@Getter	
	private Test activeTest;

	@Getter	@Setter
	private Module module;
	
	@FormField
	@Getter	@Setter
	private Question[] questions;
	
	
	@PostConstruct
	private void init(){
		module = bo.getAllModules().get(0);
	}

	public void setActiveTest(Test activeTest) {
		this.activeTest = bo.update(activeTest);
	}
	
	
	public void addTest() {
		bo.addTest();
	}
	public boolean active(){
		return activeTest != null;
	}
	
	public  List<Module> getAllModules() {
		return bo.getAllModules();
	}
	
	public  List<Question> getModuleQuestions() {
		
		return bo.getModuleQuestions(bo.refreshModule(module), activeTest);
		
	}
	
	public List<Question> getTestQuestions() {
		if (active()) {
			return bo.getTestQuestions(activeTest);
		}
		return null;
	}
	
	
	public void change(ValueChangeEvent e){
		module = (Module) e.getNewValue();
	}
	
	public String addQuestion() {

		activeTest.getQuestions().addAll(Arrays.asList(questions));
		setActiveTest(bo.update(activeTest));
		
		return null;
	}
	
	public String deleteQuestion(Question question){
		
		activeTest.getQuestions().remove(question);
		setActiveTest(bo.update(activeTest));
		
		return null;
	}	
}
