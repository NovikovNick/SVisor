package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Question;
import ru.nick.model.Test;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за тесты
 * 
 * @author NovikovNick
 *
 */
@Named("testDao")
public class TestDaoImpl extends AbstractCrudDao<Test> {

	@Inject
	@Named("questionDao")
	private SimpleCrudDao<Question> qDao;

	@Override
	public List<Test> findAll() {
		return query("Test.getAll");
	}

	@Override
	public Test update(Test entity) {
		Test test = getById(entity);

		fieldUpdateInCicle(test, entity, "Title", "Date");

		test.setQuestions(updateChild(test, entity.getQuestions(), qDao,
				"getTests"));

		return merge(test);
	}

	@Override
	protected Class<Test> getGenericClass() {
		return Test.class;
	}

	@Override
	protected String[] getUpdatableField() {
		throw new UnsupportedOperationException();
	}

}
