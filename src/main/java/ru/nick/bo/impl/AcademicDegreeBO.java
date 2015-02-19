package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

@Named("academicDegreeBO")
public class AcademicDegreeBO extends AbstaractBusinessObject<AcademicDegree> implements SimpleCrudBusinessObject<AcademicDegree> {

	@Inject
	@Named("academicDegreeDao")
	private SimpleCrudDao<AcademicDegree> dao;
	
	@Override
	protected SimpleCrudDao<AcademicDegree> getDao() {
		return dao;
	}

}
