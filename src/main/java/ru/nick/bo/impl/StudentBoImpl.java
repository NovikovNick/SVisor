package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Student;

/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за студентов
 * 
 * @author NovikovNick
 *
 */
@Named("studentBo")
public class StudentBoImpl extends AbstaractBusinessObject<Student> {

    @Inject
    @Named("studentDao")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudDao<Student> dao;

}
