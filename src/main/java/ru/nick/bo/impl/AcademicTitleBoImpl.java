package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicTitle;

@Named("academicTitleBo")
public class AcademicTitleBoImpl extends AbstaractBusinessObject<AcademicTitle>{

	@Inject
	@Named("academicTitleDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<AcademicTitle> dao;
	
}
