package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.AcademicDegree;

@Named("academicDegreeDao")
public class AcademicDegreeDaoImpl extends AbstractCrudDao<AcademicDegree>{
	
	@Override
	public List<AcademicDegree> findAll() {
		return query("AcademicDegree.getAll");
	}

	@Override
	protected Class<AcademicDegree> getGenericClass() {
		return AcademicDegree.class;
	}

	@Override
	protected String[] getUpdatableField() {
		return new String[]{"FullDegree", "ReducDegree"};
	}
}
