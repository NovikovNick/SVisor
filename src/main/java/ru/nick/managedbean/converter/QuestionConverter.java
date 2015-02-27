package ru.nick.managedbean.converter;

import javax.faces.convert.FacesConverter;

import ru.nick.model.Question;

/**
 * Конвертер вопросов
 * 
 * @author NovikovNick
 *
 */
@FacesConverter(forClass = Question.class)
public class QuestionConverter extends AbstractEntityByIdConverter<Question> {

}
