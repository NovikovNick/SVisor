package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Speciality;

@FacesConverter(forClass = Speciality.class)
public class SpecialityConverter extends AbstractEntityByIdConverter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Speciality spec = new Speciality();
		spec.setId(new Long(value));
		return spec;
	}
}
