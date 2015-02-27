package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Speciality;
/**
 * Класс-наследник {@link AbstarctManagedBean}. Отвечает за специальности
 * @author NovikovNick
 *
 */
@Component("specialityBean")
@Scope("request")
public class SpecialityBean extends AbstarctManagedBean<Speciality> {

	@Inject
	@Named("specialityBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Speciality> bo;
	
	@FormField
	@Getter	@Setter
	private Long id;
	
	@FormField
	@Getter	@Setter
	private String title;
}
