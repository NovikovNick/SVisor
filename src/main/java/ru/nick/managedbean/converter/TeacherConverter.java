package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Teacher;

@FacesConverter(forClass = Teacher.class)
public class TeacherConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Teacher t = new Teacher();
		t.setId(new Long(value));
		return t;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Teacher) {
			Teacher t = (Teacher) value;
			r = t.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
