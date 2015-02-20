package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.TestAssign;

@Named("testAssignBo")
public class TestAssignBo extends AbstaractBusinessObject<TestAssign>{
	
	@Inject
	@Named("testAssignDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<TestAssign> dao;
	
}
