package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Test;

/**
 * Конвертер теста
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Test.class)
public class TestConverter extends AbstractEntityByIdConverter<Test> {

}
