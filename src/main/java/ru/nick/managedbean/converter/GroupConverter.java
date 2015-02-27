package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Group;
/**
 * Конвертер групп
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Group.class)
public class GroupConverter extends AbstractEntityByIdConverter<Group> {

}
