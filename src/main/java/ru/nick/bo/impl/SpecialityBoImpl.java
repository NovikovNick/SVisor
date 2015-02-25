package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Speciality;


@Named("specialityBo")
public class SpecialityBoImpl extends AbstaractBusinessObject<Speciality>{
	
	@Inject
	@Named("specialityDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Speciality> dao;
	
}
