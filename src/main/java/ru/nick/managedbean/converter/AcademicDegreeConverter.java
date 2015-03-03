package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicDegree;

/**
 * Конвертер ученой степени
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = AcademicDegree.class)
public class AcademicDegreeConverter extends AbstractEntityByIdConverter<AcademicDegree> {

}
