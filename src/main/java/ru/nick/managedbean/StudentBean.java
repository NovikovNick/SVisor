package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Group;
import ru.nick.model.Student;

/**
 * Класс-наследник {@link AbstarctManagedBean}. Отвечает за студентов 
 * @author NovikovNick
 *
 */
@Component("studentBean")
@Scope("request")
public class StudentBean extends AbstarctManagedBean<Student> {

	@Inject
	@Named("studentBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Student> bo;
	
	@FormField
	@Getter	@Setter
	private Long id;
	@FormField
	@Getter	@Setter
	private String fstName;
	@FormField
	@Getter	@Setter
	private String sndName;
	@FormField
	@Getter	@Setter
	private String surname;
	@FormField
	@Getter	@Setter	
	private String login;
	@FormField
	@Getter	@Setter
	private String password;
	@FormField
	@Getter	@Setter
	private Group group;
	/**
	 * Регистрирует пользователя после добавления
	 */
	@Override
	public String add() {
		super.add();
		return "logout";
	}
}
