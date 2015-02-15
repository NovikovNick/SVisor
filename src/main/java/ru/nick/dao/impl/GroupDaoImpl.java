package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;

@Named("groupDao")
@Transactional
public class GroupDaoImpl extends AbstractCrudDao<Group> implements SimpleCrudDao<Group>{

	@Override
	public Group getById(long id) {
		return find(Group.class, id);
	}

	@Override
	public List<Group> findAll() {
		return query("Group.getAll");
	}

	@Override
	public void delete(Group entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(Group entity) {
		persist(entity);
	}

	@Override
	public Group update(Group entity) {
		Group group = getById(entity.getId());
		group.setCourse(entity.getCourse());
		group.setSpeciality(entity.getSpeciality());
		group.setTitle(entity.getTitle());
		return merge(group);
	}

}
