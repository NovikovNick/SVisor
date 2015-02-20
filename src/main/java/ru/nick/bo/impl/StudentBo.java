package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Student;

@Named("studentBo")
public class StudentBo extends AbstaractBusinessObject<Student>{
	
	@Inject
	@Named("studentDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Student> dao;
	
}
