package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Speciality;

/**
 * Конвертер специальности
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Speciality.class)
public class SpecialityConverter extends
		AbstractEntityByIdConverter<Speciality> {

}
