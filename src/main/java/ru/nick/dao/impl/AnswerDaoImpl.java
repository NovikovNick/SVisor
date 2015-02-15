package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;

@Named("answerDao")
@Transactional
public class AnswerDaoImpl extends AbstractCrudDao<Answer> implements SimpleCrudDao<Answer> {

	@Override
	public Answer getById(long id) {
		return find(Answer.class, new Long(id));
	}

	@Override
	public List<Answer> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Answer entity) {
		remove(getById(entity.getId()));
	}

	@Override
	public void add(Answer entity) {
		persist(entity);
	}

	@Override
	public Answer update(Answer entity) {
		if (entity != null) {
			
			Answer answer = getById(entity.getId());
			answer.setContent(entity.getContent());
			answer.setCorrect(entity.isCorrect());
			answer.setOwnerQuestion(entity.getOwnerQuestion());
			
			return merge(answer);
		}
		
		return null;
	}

}
