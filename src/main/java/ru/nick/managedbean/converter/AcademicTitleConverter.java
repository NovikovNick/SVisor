package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicTitle;

@FacesConverter(forClass = AcademicTitle.class)
public class AcademicTitleConverter implements javax.faces.convert.Converter {

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AcademicTitle title = new AcademicTitle();
		title.setId(new Long(value));
		return title; 
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof AcademicTitle) {
			AcademicTitle title = (AcademicTitle) value;
			r = title.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
