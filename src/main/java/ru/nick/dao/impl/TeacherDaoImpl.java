package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import ru.nick.dao.EntityDao;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;
/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за преподавателя и реализует {@link EntityDao}
 * @author NovikovNick
 *
 */
@Named("teacherDao")
public class TeacherDaoImpl  extends AbstractCrudDao<Teacher> implements EntityDao<Teacher>{

	@Inject
	@Named("disciplineDao")
	SimpleCrudDao<Discipline> discDao;
	@Inject
	@Named("groupDao")
	SimpleCrudDao<Group> groupDao;
	
	@Override
	@Cacheable("teacher")
	public List<Teacher> findAll() {
		return query("Teacher.getAll");
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public void delete(Teacher detached) {
		super.delete(detached);
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public void add(Teacher teacher) {
		super.add(teacher);
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public Teacher update(Teacher detached) {
		Teacher teacher = getById(detached);
		
		fieldUpdateInCicle(teacher, detached, "FstName", "SndName", "Surname",
				"Degree", "Title", "Login", "Password", "Inn",
				"PensionInsurance");

		teacher.setDisciplines(updateChild(teacher, detached.getDisciplines(), discDao, "getTeachers"));
		teacher.setGroups(updateChild(teacher, detached.getGroups(), groupDao, "getTeachers"));
		
		return merge(teacher);
		
	}


	@Override
	public Teacher getByLoginPassword(String login, String password) {
		//TODO: Put teacher into session
		throw new UnsupportedOperationException();
	}

	@Override
	protected String[] getUpdatableField() {
		throw new UnsupportedOperationException();
	}

}
