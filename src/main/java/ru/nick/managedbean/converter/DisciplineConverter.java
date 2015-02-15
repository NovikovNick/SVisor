package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Discipline;

@FacesConverter(forClass = Discipline.class)
public class DisciplineConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Discipline discipline = new Discipline();
		discipline.setId(new Long(value));
		return discipline;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Discipline) {
			Discipline discipline = (Discipline) value;
			r = discipline.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
