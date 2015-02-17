package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicDegree;

@FacesConverter(forClass = AcademicDegree.class)
public class AcademicDegreeConverter extends AbstractEntityByIdConverter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AcademicDegree degree = new AcademicDegree();
		degree.setId(new Long(value));
		return degree;
	}
}
