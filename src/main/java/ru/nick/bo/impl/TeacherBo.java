package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Teacher;

@Named("teacherBo")
public class TeacherBo extends AbstaractBusinessObject<Teacher>{
	
	@Inject
	@Named("teacherDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Teacher> dao;
	
}
