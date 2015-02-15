package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Speciality;

@Named("specialityDao")
@Transactional
public class SpecialityDaoImpl extends AbstractCrudDao<Speciality> implements SimpleCrudDao<Speciality>{
	
	@Override
	public List<Speciality> findAll() {
		return query("Speciality.getAll");
	}

	@Override
	public void delete(Speciality detached) {
		remove(find(Speciality.class, detached.getId()));
	}

	@Override
	public void add(Speciality speciality) {
		persist(speciality);
	}

	@Override
	public Speciality update(Speciality detached) {
		Speciality speciality = find(Speciality.class, detached.getId());
		//speciality.setId(detached.getId());
		speciality.setTitle(detached.getTitle());
		return merge(speciality);
	}

	@Override
	public Speciality getById(long id) {
		return find(Speciality.class, id);
	}

}
