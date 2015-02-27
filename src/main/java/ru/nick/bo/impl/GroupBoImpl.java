package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за группы
 * @author NovikovNick
 *
 */
@Named("groupBo")
public class GroupBoImpl extends AbstaractBusinessObject<Group>{

	@Inject
	@Named("groupDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Group> dao;
	
}