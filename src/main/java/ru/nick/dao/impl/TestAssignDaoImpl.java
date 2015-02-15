package ru.nick.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
import ru.nick.model.TestAssign;

@Named("assignDao")
@Transactional
public class TestAssignDaoImpl extends AbstractCrudDao<TestAssign> implements SimpleCrudDao<TestAssign> {

	@Inject
	@Named("groupDao")
	SimpleCrudDao<Group> groupDao;
	
	@Override
	public TestAssign getById(long id) {
		return find(TestAssign.class, new Long(id));
	}

	@Override
	public List<TestAssign> findAll() {
		return query("TestAssign.getAll");
	}

	@Override
	public void delete(TestAssign entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(TestAssign entity) {
		persist(entity);
	}

	@Override
	public TestAssign update(TestAssign entity) {
		TestAssign testAssign = getById(entity.getId());
		
		fieldUpdateInCicle(testAssign, entity, 
				"Title", "Description", "Date_start", "Date_end", "Passing_score",
				"Completion_time", "Attempts", "Author", "Test");

		//GROUP

		Set<Group> groupDetached = entity.getGroups();
		Set<Group> groupAttached = new HashSet<Group>();

		for (Group group : groupDetached) {
			groupAttached.add(groupDao.getById(group.getId()));
		}

		for (Group group : groupAttached) {
			
			Set<TestAssign> testAssigns = group.getTestAssign();
			if (!testAssigns.contains(group)) {
				testAssigns.add(testAssign);
				break;
			}
		}
		testAssign.setGroups(groupAttached);
		
		
		return merge(testAssign);
	}

}
