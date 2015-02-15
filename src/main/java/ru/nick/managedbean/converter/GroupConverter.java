package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Group;

@FacesConverter(forClass = Group.class)
public class GroupConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Group group = new Group();
		group.setId(new Long(value));
		return group;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Group) {
			Group group = (Group) value;
			r = group.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
