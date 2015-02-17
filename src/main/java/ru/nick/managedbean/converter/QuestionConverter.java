package ru.nick.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ru.nick.model.Question;

@FacesConverter(forClass = Question.class)
public class QuestionConverter extends AbstractEntityByIdConverter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Question question = new Question();
		question.setId(new Long(value));
		System.out.println("getAsObject " + question);
		return question;
	}
}
