package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Group;

@Named("groupDao")
public class GroupDaoImpl extends AbstractCrudDao<Group>{

	
	@Override
	public List<Group> findAll() {
		return query("Group.getAll");
	}

	@Override
	protected Class<Group> getGenericClass() {
		return Group.class;
	}
	
	@Override
	protected String[] getUpdatableField() {
		return new String[]{ "Course", "Speciality", "Title"};
	}

}
