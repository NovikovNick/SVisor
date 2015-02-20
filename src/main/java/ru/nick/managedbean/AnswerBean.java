package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Answer;

@Named("answerBean")
public class AnswerBean extends AbstarctManagedBean<Answer> {

	@Inject
	@Named("answerBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Answer> bo;
	
	@FormField
	@Getter	@Setter
	private String content;
	
	@FormField
	@Getter	@Setter
	private boolean correct;
	
	
}
