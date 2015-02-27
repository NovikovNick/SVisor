package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;
/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за ученую степень
 * @author NovikovNick
 *
 */
@Named("academicDegreeBo")
public class AcademicDegreeBoImpl extends AbstaractBusinessObject<AcademicDegree>{
	
	@Inject
	@Named("academicDegreeDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<AcademicDegree> dao;
	
}
