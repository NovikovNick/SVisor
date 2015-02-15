package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Speciality;

@FacesConverter(forClass = Speciality.class)
public class SpecialityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Speciality spec = new Speciality();
		spec.setId(new Long(value));
		return spec;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Speciality) {
			Speciality spec = (Speciality) value;
			r = spec.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
