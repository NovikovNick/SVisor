package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Question;

@Named("questionBo")
public class QuestionBoImpl extends AbstaractBusinessObject<Question>{
	
	@Inject
	@Named("questionDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Question> dao;
	
}
