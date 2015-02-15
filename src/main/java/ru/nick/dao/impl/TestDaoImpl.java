package ru.nick.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Question;
import ru.nick.model.Teacher;
import ru.nick.model.Test;

@Named("testDao")
@Transactional
public class TestDaoImpl extends AbstractCrudDao<Test> implements SimpleCrudDao<Test> {

	@Inject
	@Named("questionDao")
	private SimpleCrudDao<Question> qDao;
	
	@Override
	public Test getById(long id) {
		return find(Test.class, new Long(id));
	}

	@Override
	public List<Test> findAll() {
		return query("Test.getAll");
	}

	@Override
	public void delete(Test entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(Test entity) {
		persist(entity);
	}

	@Override
	public Test update(Test entity) {//Date?
		Test test = getById(entity.getId());
		
		System.out.println("update: "+test.getTitle() + " ==> " + entity.getTitle());
		test.setTitle(entity.getTitle());
		System.out.println("result: "+test.getTitle());
		
		test.setDate(entity.getDate());
		
		//In teacher without this  code does not update the entity
		//Question update start
		Set<Question> qDetached = entity.getQuestions();
		Set<Question> aAttached = new HashSet<Question>();
		
		for (Question q : qDetached) 
			aAttached.add(qDao.getById(q.getId()));
				
		for (Question q : aAttached) {			
			Set<Test> tests = q.getTests();
			if (!tests.contains(q)) {
				tests.add(test);
				break;
			}
		}		
		test.setQuestions(aAttached);
		// === end ===
		
		
		return merge(test);
	}

}
