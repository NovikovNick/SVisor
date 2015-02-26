package ru.nick.managedbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.impl.ResultBoImpl;
import ru.nick.dao.EntityDao;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;
import ru.nick.security.Entity;

@Component("student")
@Scope("session")
public class StudentSessionBean {
	
	@Inject
	@Named("studentEntityDao")
	private EntityDao<Student> sDao;
	
	@Inject
	@Named("assignDao")
	private SimpleCrudDao<TestAssign> atDao;
	
	@Inject
	@Named("ResultBO")
	private ResultBoImpl resultBo;
	
	private Student student;
	private TestAssign currentAssignment;
	private int currentAttempt;
	private Iterator<Question> questions;
	private Question currentQuestion;
	
	@PostConstruct
	private void refresh(){
		Entity entity = (Entity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entity");
		student = sDao.getByLoginPassword(entity.getLogin(), entity.getPassword());
	}

	// *********** Getter/setter methods ****************//
	//*************       START      ********************//
	public int getCurrentAttempt() {return currentAttempt;}
	public void setCurrentAttempt(int currentAttempt) {this.currentAttempt = currentAttempt;}
	
	public Student getStudent() {return student;}
	public void setStudent(Student student) {this.student = student;}
	
	public TestAssign getCurrentAssignment() {return currentAssignment;}
	public void setCurrentAssignment(TestAssign currentAssignment) {this.currentAssignment = currentAssignment;}

	public List<TestAssign> getAssignes() {
		//TMP query. TODO: jpql for search by group
		List<TestAssign> res = new ArrayList<TestAssign>();
		
		for (TestAssign assignment : atDao.findAll()) {
			if (assignment.getGroups().contains(student.getGroup())) {
				res.add(assignment);
			}
		}
		return res;
	}
	public Question getCurrentQuestion() {return currentQuestion;}
	//*************        END      ********************//
	
	
	
	
	public String start(TestAssign assign) {
		currentAssignment = assign;
	//	questions = assign.getTest().tmpQ().iterator();
		currentQuestion = questions.next();
		
		resultBo.setCurrentAttempt(getCurrentAttempt());//TODO:!!!
		
		resultBo.setStudent(getStudent());
		resultBo.setTest(getCurrentAssignment().getTest());
		
		return null;
	}
	
	public String done() {
		currentAssignment = null;
		questions = null;
		currentQuestion = null;
		resultBo.done();
		return null;
	}
	
	public boolean isActiveAssign() {
		return currentAssignment != null;
	}
	
	public void next(Answer answer) {
		
		resultBo.getResult().put(currentQuestion, answer.isCorrect());
		
		
		if (questions.hasNext()) {
			currentQuestion = questions.next();
		}else {
			done();
		}
		
	}
}
