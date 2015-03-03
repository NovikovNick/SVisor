package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicTitle;

/**
 * Конвертер ученого звания
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = AcademicTitle.class)
public class AcademicTitleConverter extends AbstractEntityByIdConverter<AcademicTitle> {

}
