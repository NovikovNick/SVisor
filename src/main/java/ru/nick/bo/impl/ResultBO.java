package ru.nick.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Question;
import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.Test;

@Named("ResultBO")
public class ResultBO {
	
	private final double MAX_RES = 100;
	@Inject
	@Named("resultDao")
	SimpleCrudDao<Result> dao;

	private Student student;
	private int currentAttempt;
	private Map<Question, Boolean> result;
	private Test test;
	
	@PostConstruct
	private void init(){
		result = new LinkedHashMap<Question, Boolean>();
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getCurrentAttempt() {
		return currentAttempt;
	}

	public void setCurrentAttempt(int currentAttempt) {
		this.currentAttempt = currentAttempt;
	}

	public Map<Question, Boolean> getResult() {
		return result;
	}

	public void setResult(Map<Question, Boolean> result) {
		this.result = result;
	}

	
	
	public List<Result> findAll() {
		return dao.findAll();
	}

	public void done() {
		Result result = new Result();
		result.setAttempt(getCurrentAttempt());
		result.setStudent(getStudent());
		result.setResult(calculate(getResult()));
		result.setTest(getTest());
		dao.add(result);
	}

	private int calculate(Map<Question, Boolean> result2) {
		int max = result2.size();
		int res = 0;
		for (Boolean correct : result2.values()) {
			if (correct.booleanValue()) res++;
		}
		return (int) (res==0 ? 0 : res/(max/MAX_RES));
	}
	
	
}
