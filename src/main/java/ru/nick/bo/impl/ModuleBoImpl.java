package ru.nick.bo.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;

/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за модули
 * 
 * @author NovikovNick
 *
 */
@Named("moduleBo")
public class ModuleBoImpl extends AbstaractBusinessObject<Module> {

    @Inject
    @Named("moduleDao")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudDao<Module> dao;

    @Override
    public void add(Module entity) {
        entity.setDate(getCurrentDate());
        super.add(entity);
    }

}