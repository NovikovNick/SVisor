package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Question;

@FacesConverter(forClass = Question.class)
public class QuestionConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Question question = new Question();
		question.setId(new Long(value));
		System.out.println("getAsObject " + question);
		return question;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String r = "";
		if (value instanceof Question) {
			Question question = (Question) value;
			r = question.getId() + "";
		} else if (value instanceof String) {
			r = (String) value;
		}
		return r;
	}

}
