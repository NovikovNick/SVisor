package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Test;

@FacesConverter(forClass = Test.class)
public class TestConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Test t = new Test();
		t.setId(new Long(value));
		return t;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Test) {
			Test t = (Test) value;
			r = t.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
