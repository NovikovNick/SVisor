package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Question;

@Named("questionDao")
@Transactional
public class QuestionDaoImpl  extends AbstractCrudDao<Question> implements SimpleCrudDao<Question>{

	//Not use
	public List<Question> getByModuleId(Long id) {
		return query("Question.getByModuleId", new Long[]{id});
	}
	
	
	@Override
	public Question getById(long id) {
		return find(Question.class, id);
	}

	@Override
	public List<Question> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Question entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(Question entity) {
		persist(entity);
	}

	@Override
	public Question update(Question entity) {
		Question q = getById(entity.getId());
		q.setContent(entity.getContent());
		q.setDifficult(entity.getDifficult());
		q.setOwnerModule(entity.getOwnerModule());
		return merge(q);
	}

}
