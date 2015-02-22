package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import ru.nick.model.Module;

@Named("moduleDao")
public class ModuleDaoImpl extends AbstractCrudDao<Module>{

	@Override
	@Cacheable("module")
	public List<Module> findAll() {
		return query("Module.getAll");
	}

	@Override
	@CacheEvict(value = "module", allEntries = true)
	public void delete(Module entity) {
		super.delete(getById(entity.getId()));
	}

	@Override
	@CacheEvict(value = "module", allEntries = true)
	public void add(Module entity) {
		super.add(entity);
	}

	@Override
	@CacheEvict(value = "module", allEntries = true)
	public Module update(Module entity) {
		return super.update(entity);
	}

	@Override
	protected String[] getUpdatableField() {
		return new String[]{ "Title"};
	}

}
