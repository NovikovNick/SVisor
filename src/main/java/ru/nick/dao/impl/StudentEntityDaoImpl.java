package ru.nick.dao.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.EntityDao;
import ru.nick.model.Student;

@Named("studentEntityDao")
@Transactional
public class StudentEntityDaoImpl extends AbstractCrudDao<Student> implements EntityDao<Student> {

	@Override
	public Student getByLoginPassword(String login, String password) {
		System.out.println("!"+login + " " + password);
		return query("Student.getByLoginPassword", new String[]{login, password}).get(0);
		
	}

}
