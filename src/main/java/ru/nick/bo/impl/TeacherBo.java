package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Group> gDao;
	
	@Inject
	@Named("disciplineBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Discipline> dDao;

	@Override
	public List<Discipline> getDisciplineList(Teacher teacher) {
		return asOrderList(teacher.getDisciplines());
	}

	@Override
	public List<Group> getGroupList(Teacher teacher) {
		return asOrderList(teacher.getGroups());
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

	@Override
	public List<Discipline> getAllDisciplines(Teacher teacher) {
		List<Discipline> neo = new ArrayList<>();
		List<Discipline> old = getDisciplineList(teacher);
		for (Discipline discipline : dDao.findAll()) {
			if (!old.contains(discipline)) {
				neo.add(discipline);
			}
		}
		return neo;
	}

	@Override
	public List<Group> getAllGroups(Teacher teacher) {
		List<Group> res = gDao.findAll();
		res.removeAll(getGroupList(teacher));
		return res;
	}
	
	
}
