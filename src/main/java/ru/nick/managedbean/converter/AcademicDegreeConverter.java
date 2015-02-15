package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicDegree;

@FacesConverter(forClass = AcademicDegree.class)
public class AcademicDegreeConverter implements Converter {

	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AcademicDegree degree = new AcademicDegree();
		degree.setId(new Long(value));
		return degree;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof AcademicDegree) {
			AcademicDegree degree = (AcademicDegree) value;
			r = degree.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}
}
