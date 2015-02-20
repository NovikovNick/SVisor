package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;

@Named("answerBo")
public class AnswerBo extends AbstaractBusinessObject<Answer>{

	@Inject
	@Named("answerDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Answer> dao;
	
}
