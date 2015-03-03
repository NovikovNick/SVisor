package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Speciality;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за специальностиЩ
 * 
 * @author NovikovNick
 *
 */
@Named("specialityDao")
public class SpecialityDaoImpl extends AbstractCrudDao<Speciality> {

    @Override
    public List<Speciality> findAll() {
        return query("Speciality.getAll");
    }

    @Override
    protected Class<Speciality> getGenericClass() {
        return Speciality.class;
    }

    @Override
    protected String[] getUpdatableField() {
        return new String[] { "Title" };
    }

}
