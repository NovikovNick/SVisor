package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Discipline;

@Named("disciplineDao")
@Transactional
public class DisciplineDaoImpl  extends AbstractCrudDao<Discipline> implements SimpleCrudDao<Discipline> {

	@Override
	@Cacheable("discipline")
	public List<Discipline> findAll() {
		return query("Discipline.getAll");
	}

	@Override
	@CacheEvict(value = "discipline", allEntries = true)
	public void delete(Discipline detached) {
		remove(find(Discipline.class, detached.getId()));		
	}

	@Override
	@CacheEvict(value = "discipline", allEntries = true)
	public void add(Discipline discipline) {
		persist(discipline);
	}

	@Override
	@CacheEvict(value = "discipline", allEntries = true)
	public Discipline update(Discipline detached) {
		Discipline discipline = find(Discipline.class, detached.getId());
		discipline.setTitle(detached.getTitle());
		return merge(discipline);
	}

	@Override
	public Discipline getById(long id) {
		return find(Discipline.class, id);
	}

}
