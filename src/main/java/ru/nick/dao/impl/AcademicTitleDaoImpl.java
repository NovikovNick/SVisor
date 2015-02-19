package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.AcademicTitle;

@Named("academicTitleDao")
public class AcademicTitleDaoImpl extends AbstractCrudDao<AcademicTitle>{

	
	@Override
	public List<AcademicTitle> findAll() {
		return query("AcademicTitle.getAll");
	}

	@Override
	protected Class<AcademicTitle> getGenericClass() {
		return AcademicTitle.class;
	}

	@Override
	protected String[] getUpdatableField() {
		return new String[]{"FullTitle", "ReducTitle"};
	}

}
