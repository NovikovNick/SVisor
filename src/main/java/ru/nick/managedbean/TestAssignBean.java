package ru.nick.managedbean;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Group;
import ru.nick.model.Teacher;
import ru.nick.model.Test;
import ru.nick.model.TestAssign;

@Component("assignBean")
@Scope("request")
public class TestAssignBean extends AbstarctManagedBean<TestAssign> {

	@Inject
	@Named("testAssignBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<TestAssign> bo;
	@FormField
	@Getter	@Setter
	private String title;
	@FormField
	@Getter	@Setter
	private String description;
	@FormField
	@Getter	@Setter
	private Date date_start;//not use
	@FormField
	@Getter	@Setter
	private Date date_end;//not use
	@FormField
	@Getter	@Setter
	private int passing_score;
	@FormField
	@Getter	@Setter
	private int completion_time;
	@FormField
	@Getter	@Setter
	private int attempts;	
	@FormField
	@Getter	@Setter
	private Teacher author;
	@FormField
	@Getter	@Setter
	private Test test;
	@FormField
	@Getter	@Setter
	private Group[] groups;
	
	
	
	@Override
	public String add() {
		TestAssign ta = new TestAssign();
		fillFields(ta);
		ta.setGroups(new HashSet<Group>(Arrays.asList(getGroups())));
		getBo().add(ta);
		clearForm();
		refresh();
		return null;
	}
	
}
