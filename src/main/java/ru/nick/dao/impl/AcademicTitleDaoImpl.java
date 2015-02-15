package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicTitle;

@Named("academicTitleDao")
@Transactional
public class AcademicTitleDaoImpl extends AbstractCrudDao<AcademicTitle> implements SimpleCrudDao<AcademicTitle> {

	
	@Override
	public List<AcademicTitle> findAll() {
		return query("AcademicTitle.getAll");
	}

	@Override
	public void delete(AcademicTitle detached) {
		remove(find(AcademicTitle.class, detached.getId()));	
	}

	@Override
	public void add(AcademicTitle academicTitle) {
		persist(academicTitle);
	}

	@Override
	public AcademicTitle update(AcademicTitle detached) {
		AcademicTitle academicTitle = find(AcademicTitle.class, detached.getId());
		academicTitle.setFullTitle(detached.getFullTitle());
		academicTitle.setReducTitle(detached.getReducTitle());
		return merge(academicTitle);
	}

	@Override
	public AcademicTitle getById(long id) {
		return find(AcademicTitle.class, id);
	}

}
