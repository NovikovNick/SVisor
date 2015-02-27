package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;
/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за дисциплины
 * @author NovikovNick
 *
 */
@Named("disciplineBo")
public class DisciplineBoImpl extends AbstaractBusinessObject<Discipline>{

	@Inject
	@Named("disciplineDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Discipline> dao;
	
}
