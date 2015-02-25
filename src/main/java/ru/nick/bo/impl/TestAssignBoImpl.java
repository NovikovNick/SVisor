package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.TestAssign;

@Named("testAssignBo")
public class TestAssignBoImpl extends AbstaractBusinessObject<TestAssign>{
	
	@Inject
	@Named("testAssignDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<TestAssign> dao;

	@Override
	public void add(TestAssign entity) {
		entity.setDate_start(getCurrentDate());
		entity.setDate_end(getCurrentDate(3));//tmp
		super.add(entity);
	}
	
}
