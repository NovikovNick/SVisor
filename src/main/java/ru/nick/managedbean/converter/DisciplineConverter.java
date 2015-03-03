package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Discipline;

/**
 * Конвертер дисциплины
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Discipline.class)
public class DisciplineConverter extends AbstractEntityByIdConverter<Discipline> {

}
