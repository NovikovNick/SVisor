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
import ru.nick.model.Speciality;

@Component("groupBean")
@Scope("request")
public class GroupBean extends AbstarctManagedBean<Group> {

	@Inject
	@Named("groupBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Group> bo;
	
	@FormField
	@Getter	@Setter
	private String title;
	
	@FormField
	@Getter	@Setter
	private int course;
	
	@FormField
	@Getter	@Setter
	private Speciality speciality;
	
}
