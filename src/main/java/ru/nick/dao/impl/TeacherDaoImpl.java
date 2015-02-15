package ru.nick.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

@Named("teacherDao")
@Transactional
public class TeacherDaoImpl  extends AbstractCrudDao<Teacher> implements SimpleCrudDao<Teacher> {

	@Inject
	@Named("disciplineDao")
	SimpleCrudDao<Discipline> discDao;
	@Inject
	@Named("groupDao")
	SimpleCrudDao<Group> groupDao;
	
	@Override
	@Cacheable("teacher")
	public List<Teacher> findAll() {
		List<Teacher> res = query("Teacher.getAll");
		return res;
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public void delete(Teacher detached) {
		remove(find(Teacher.class, detached.getId()));
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public void add(Teacher teacher) {
		persist(teacher);
	}

	@Override
	@CacheEvict(value = "teacher", allEntries = true)
	public Teacher update(Teacher detached) {
		Teacher teacher = find(Teacher.class, detached.getId());
		
		teacher.setId(detached.getId());//?
		
		teacher.setFstName(detached.getFstName());
		teacher.setSndName(detached.getSndName());
		teacher.setSurname(detached.getSurname());
		
		teacher.setDegree(detached.getDegree());
		teacher.setTitle(detached.getTitle());
		
		teacher.setLogin(detached.getLogin());
		teacher.setPassword(detached.getPassword());
		
		teacher.setInn(detached.getInn());
		teacher.setPensionInsurance(detached.getPensionInsurance());
				
		//DISCIPLINE
		
		Set<Discipline> discDetached = detached.getDisciplines();
		Set<Discipline> discAttached = new HashSet<Discipline>();
		
		for (Discipline discipline : discDetached) {
			discAttached.add(discDao.getById(discipline.getId()));
		}
		
		for (Discipline discipline : discAttached) {
			
			Set<Teacher> teachers = discipline.getTeachers();
			if (!teachers.contains(discipline)) {
				teachers.add(teacher);
				break;
			}
		}		
		teacher.setDisciplines(discAttached);
		
		//GROUP
		
		Set<Group> groupDetached = detached.getGroups();
		Set<Group> groupAttached = new HashSet<Group>();
		
		for (Group group : groupDetached) {
			groupAttached.add(groupDao.getById(group.getId()));
		}
		
		for (Group group : groupAttached) {
			
			Set<Teacher> teachers = group.getTeachers();
			if (!teachers.contains(group)) {
				teachers.add(teacher);
				break;
			}
		}		
		teacher.setGroups(groupAttached);
		
		return merge(teacher);
		
	}

	@Override
	public Teacher getById(long id) {
		return find(Teacher.class, id);
	}

}
