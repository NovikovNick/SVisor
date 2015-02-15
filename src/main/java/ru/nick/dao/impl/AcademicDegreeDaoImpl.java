package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

@Named("academicDegreeDao")
@Transactional
public class AcademicDegreeDaoImpl extends AbstractCrudDao<AcademicDegree> implements SimpleCrudDao<AcademicDegree> {//AbstractJpaDao  AcademicDegreeDao
	
	@Override
	public List<AcademicDegree> findAll() {
		return query("AcademicDegree.getAll");
	}

	@Override
	public void delete(AcademicDegree detached) {
		remove(find(AcademicDegree.class, detached.getId()));	
	}

	@Override
	public void add(AcademicDegree academicDegree) {
		persist(academicDegree);
	}

	@Override
	public AcademicDegree update(AcademicDegree detached) {
		AcademicDegree academicDegree = find(AcademicDegree.class, detached.getId());
		academicDegree.setFullDegree(detached.getFullDegree());
		academicDegree.setReducDegree(detached.getReducDegree());
		return merge(academicDegree);
	}

	@Override
	public AcademicDegree getById(long id) {
		return find(AcademicDegree.class, id);
	}

}
