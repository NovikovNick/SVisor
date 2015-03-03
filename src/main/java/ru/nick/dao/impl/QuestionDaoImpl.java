package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Question;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за вопросы
 * 
 * @author NovikovNick
 *
 */
@Named("questionDao")
public class QuestionDaoImpl extends AbstractCrudDao<Question> {

    @Override
    public List<Question> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Class<Question> getGenericClass() {
        return Question.class;
    }

    @Override
    protected String[] getUpdatableField() {
        return new String[] { "Content", "Difficult", "OwnerModule" };
    }

}
