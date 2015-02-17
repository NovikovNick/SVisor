package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import ru.nick.model.AcademicTitle;

@FacesConverter(forClass = AcademicTitle.class)
public class AcademicTitleConverter extends AbstractEntityByIdConverter {

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AcademicTitle title = new AcademicTitle();
		title.setId(new Long(value));
		return title; 
	}

}
