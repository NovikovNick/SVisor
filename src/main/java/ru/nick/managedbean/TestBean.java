package ru.nick.managedbean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Test;

@Component("testBean")
@Scope("view")
public class TestBean {
//
//	@Inject
//	@Named("testDao")
//	SimpleCrudDao<Test> dao;
//	
//	@Inject
//	@Named("moduleBean")
//	private ModuleBean moduleBean;
//	
//	private String title;
//	
//	private Test activeTest;
//	
//	private Module module;
//	
//	private Question[] questions;
//	
//	private List<Test> allTests;
//	
//	@PostConstruct
//	private void init(){
//		refreshTests();
//		module = moduleBean.getAllModules().get(0);
//	}
//
//	
//	
//	// ************* Getters/Setters ********************//
//	// *************        START    ********************//
//	public String getTitle() {return title;}
//	public void setTitle(String title) {this.title = title;}
//	
//	
//	public Test getActiveTest() {return activeTest;}
//	public void setActiveTest(Test activeTest) {
//		this.activeTest = dao.update(activeTest);
//		questions = null;
//	}
//	
//	public Module getModule() {return module;}
//	public void setModule(Module module) {this.module = module;}
//	
//	public Question[] getQuestions() {return questions;}
//	public void setQuestions(Question[] questions) {this.questions = questions;}
//	
//	// *************       END       ********************//
//
//	
//	
//	
//	// ************* User's CRUD methods ****************//
//	// ************* START ********************//
//	/** Create */
//	public void add() {
//		Test test = new Test();
//		test.setTitle(getTitle());
//		
//		test.setDate(getCurrentDate());//BO?
//		
//		dao.add(test);
//		clearForm();
//		refreshTests();
//	}
//	/** Read */
//	public List<Test> getAllTests() {
//		return allTests;
//	}
//	/** Update */
//	public String update(Test test) {
//		setActiveTest(dao.update(test));
//		return null;
//	}
//	/** Delete */
//	public String delete(Test test) {
//		
//		dao.delete(test);
//		refreshTests();
//		return null;
//	}
//
//	// ************* END ********************//
// 
//	
//	//BO?
//	private java.sql.Date getCurrentDate() {
//		java.util.Date today = new java.util.Date();
//		return new java.sql.Date(today.getTime());
//	}
//	private void refreshTests() {
//		allTests = dao.findAll();
//	}
//
//	// *********** User's JSF-form methods **************//
//	// ************* START ********************//
//	private void clearForm() {
//		setTitle("");
//	}
//	public void addTest() {
//		setTitle("Новый тест");
//		add();
//		clearForm();
//	}
//	public boolean active(){
//		return !(activeTest == null);
//	}
//	
//	public  List<Module> getAllModules() {
//		return moduleBean.getAllModules();
//	}
//	
//	public  List<Question> getModuleQuestions() {
//		List<Module> result = moduleBean.getAllModules();
//		for (Module module : result) {
//			if (module.equals(this.module)) {
//				
//				List<Question> res = module.tmpQ();
//				res.removeAll(activeTest.getQuestions());
//				return res;
//			}
//		}
//		return null;
//	}
//	
//	
//	
//	
//	public void change(ValueChangeEvent e){
//		module = (Module) e.getNewValue();
//	}
//	
//	public String addQuestion() {
//		System.out.println("\nquestions:\n\n     " + questions);
//		Set<Question> set = new HashSet<>();
//		
//		for (int i = 0; i < questions.length; i++) {
//			set.add(questions[i]);
//		}
//		
//		
//		Set<Question> oldValue = activeTest.getQuestions();
//		oldValue.addAll(set);		
//		update(activeTest);
//		
//		questions = null;
//		return null;
//	}
//	public String deleteQuestion(Question question){
//		
//		activeTest.getQuestions().remove(question);
//		update(activeTest);
//		
//		return null;
//	}
//	
//	
//	// ************* END ********************//
//
//	
//	
//	
//	
//	
//	
//	
//
//	
	
}
