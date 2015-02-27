package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Teacher;

/**
 * Конвертер специальности
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Teacher.class)
public class TeacherConverter extends AbstractEntityByIdConverter<Teacher> {
}
