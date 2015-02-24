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
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.bo.TeacherInt;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Identifiable;
import ru.nick.model.Teacher;

@Named("teacherBo")
public class TeacherBo extends AbstaractBusinessObject<Teacher> implements TeacherInt{
	
	@Inject
	@Named("teacherDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Teacher> dao;
	
	@Inject
	@Named("groupBo")
	private SimpleCrudBusinessObject<Group> gBo;
	
	@Inject
	@Named("disciplineBo")
	private SimpleCrudBusinessObject<Discipline> dBo;

	
	
	
	
	@Override
	public List<Discipline> getDisciplineList(Teacher teacher) {
		return asOrderList(teacher.getDisciplines());
	}

	@Override
	public List<Group> getGroupList(Teacher teacher) {
		return asOrderList(teacher.getGroups());
	}	
	

	

	@Override
	public List<Discipline> getAllDisciplines(Teacher teacher) {
		List<Discipline> res = dBo.findAll();
		res.removeAll(getDisciplineList(teacher));
		return res;
	}

	@Override
	public List<Group> getAllGroups(Teacher teacher) {
		List<Group> res = gBo.findAll();
		res.removeAll(getGroupList(teacher));
		return res;
		
	}
}


