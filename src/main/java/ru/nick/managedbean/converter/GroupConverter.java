package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Group;

@FacesConverter(forClass = Group.class)
public class GroupConverter extends AbstractEntityByIdConverter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Group group = new Group();
		group.setId(new Long(value));
		return group;
	}

}
