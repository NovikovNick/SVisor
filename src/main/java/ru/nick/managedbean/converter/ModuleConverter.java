package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Module;

@FacesConverter(forClass = Module.class)
public class ModuleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Module module = new Module();
		module.setId(new Long(value));
		return module;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Module) {
			Module module = (Module) value;
			r = module.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
