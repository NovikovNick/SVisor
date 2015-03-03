package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.dao.EntityDao;
import ru.nick.model.Student;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за студента и реализует
 * {@link EntityDao}
 * 
 * @author NovikovNick
 *
 */
@Named("studentDao")
public class StudentEntityDaoImpl extends AbstractCrudDao<Student> implements EntityDao<Student> {

    @Override
    public Student getByLoginPassword(String login, String password) {
        System.out.println("!" + login + " " + password);
        return query("Student.getByLoginPassword", new String[] { login, password }).get(0);

    }

    @Override
    public List<Student> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Class<Student> getGenericClass() {
        return Student.class;
    }

    @Override
    protected String[] getUpdatableField() {
        return new String[] { "FstName", "SndName", "Surname", "Group", "Login", "Password" };
    }

}
