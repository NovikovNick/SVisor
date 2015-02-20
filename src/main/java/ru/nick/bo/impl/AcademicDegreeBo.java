package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

@Named("academicDegreeBo")
public class AcademicDegreeBo extends AbstaractBusinessObject<AcademicDegree>{
	
	@Inject
	@Named("academicDegreeDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<AcademicDegree> dao;
	
}
