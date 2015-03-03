package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import ru.nick.model.Discipline;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за дисциплины
 * 
 * @author NovikovNick
 *
 */
@Named("disciplineDao")
public class DisciplineDaoImpl extends AbstractCrudDao<Discipline> {

    @Override
    protected Class<Discipline> getGenericClass() {
        return Discipline.class;
    }

    @Override
    @Cacheable("discipline")
    public List<Discipline> findAll() {
        return query("Discipline.getAll");
    }

    @Override
    @CacheEvict(value = "discipline", allEntries = true)
    public void delete(Discipline detached) {
        super.delete(detached);
    }

    @Override
    @CacheEvict(value = "discipline", allEntries = true)
    public void add(Discipline discipline) {
        super.add(discipline);
    }

    @Override
    protected String[] getUpdatableField() {
        return new String[] { "Title" };
    }
}
