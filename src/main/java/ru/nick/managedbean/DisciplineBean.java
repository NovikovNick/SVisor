package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Discipline;

@Component("disciplineBean")
@Scope("request")
public class DisciplineBean extends AbstarctManagedBean<Discipline> {

	@Inject
	@Named("disciplineBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Discipline> bo;
	
	@FormField
	@Getter	@Setter
	private String title;
	
}
