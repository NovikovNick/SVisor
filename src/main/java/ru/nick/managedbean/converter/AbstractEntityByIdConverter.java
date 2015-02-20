package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.nick.model.Identifiable;

public abstract class AbstractEntityByIdConverter implements Converter {

	@Override
	public abstract Object getAsObject(FacesContext context, UIComponent component, String value);

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String r = "";
		if (value instanceof Identifiable) {
			Identifiable entity = (Identifiable) value;
			r = entity.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
