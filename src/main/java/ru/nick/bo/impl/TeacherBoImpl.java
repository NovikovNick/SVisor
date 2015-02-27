package ru.nick.bo.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.bo.TeacherBo;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за преподавателей и
 * реализует {@link TeacherBo}
 * 
 * @author NovikovNick
 *
 */
@Named("teacherBo")
public class TeacherBoImpl extends AbstaractBusinessObject<Teacher> implements
		TeacherBo {

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
