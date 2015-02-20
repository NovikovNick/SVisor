package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;

@Named("disciplineBo")
public class DisciplineBo extends AbstaractBusinessObject<Discipline>{

	@Inject
	@Named("disciplineDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Discipline> dao;
	
}
