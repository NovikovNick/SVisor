package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Student;

@Named("studentDao")
@Transactional
public class StudentDaoImpl extends AbstractCrudDao<Student> implements SimpleCrudDao<Student> {

	@Override
	public Student getById(long id) {
		return find(Student.class, new Long(id));
	}

	@Override
	public List<Student> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Student entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(Student entity) {
		persist(entity);
	}

	@Override
	public Student update(Student entity) {
		Student student = getById(entity.getId());
		
		student.setId(entity.getId());//?
		
		student.setFstName(entity.getFstName());
		student.setSndName(entity.getSndName());
		student.setSurname(entity.getSurname());
		
		student.setGroup(entity.getGroup());
		
		student.setLogin(entity.getLogin());
		student.setPassword(entity.getPassword());
		
		return merge(student);
	}

}
