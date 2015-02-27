package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Answer;
/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за ответы
 * @author NovikovNick
 *
 */
@Named("answerDao")
public class AnswerDaoImpl extends AbstractCrudDao<Answer>{

	
	@Override
	public List<Answer> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Class<Answer> getGenericClass() {
		return Answer.class;
	}
	
	@Override
	protected String[] getUpdatableField() {
		return new String[]{"Content", "Correct", "OwnerQuestion"};
	}
	

}
