package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;

@Named("moduleDao")
@Transactional
public class ModuleDaoImpl extends AbstractCrudDao<Module> implements SimpleCrudDao<Module> {

	
	
	@Override
	public Module getById(long id) {
		return find(Module.class, id);
	}

	@Override
	@Cacheable("module")
	public List<Module> findAll() {
		return query("Module.getAll");
	}

	@Override
	@CacheEvict(value = "module", allEntries = true)
	public void delete(Module entity) {
		remove(getById(entity.getId()));
	}

	@Override
	@CacheEvict(value = "module", allEntries = true)
	public void add(Module entity) {
		persist(entity);
	}

	//@Override
	@CacheEvict(value = "module", allEntries = true)
	public Module update(Module entity) {
		Module module = getById(entity.getId());
		module.setTitle(entity.getTitle());
		return merge(module);
	}

}
