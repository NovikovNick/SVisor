package ru.nick.managedbean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.bo.TeacherInt;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;
import ru.nick.model.AcademicTitle;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

@Component("teacherBean")
@Scope("request")
public class TeacherBean extends AbstarctManagedBean<Teacher> {

	@Inject
	@Named("teacherBo")
	@Getter(AccessLevel.PROTECTED)
	private TeacherInt bo;
	
	private static final String TEST = "test";
	
	@FormField
	@Getter	@Setter
	private long id;
	@FormField
	@Getter	@Setter
	private String fstName = TEST;
	@FormField
	@Getter	@Setter
	private String sndName = TEST;
	@FormField
	@Getter	@Setter
	private String surname = TEST;
	@FormField
	@Getter	@Setter	
	private String login = TEST;
	@FormField
	@Getter	@Setter
	private String password = TEST;
	@FormField
	@Getter	@Setter
	private AcademicDegree degree;	
	@FormField
	@Getter	@Setter
	private AcademicTitle title;
	@FormField
	@Getter	@Setter
	private BigInteger inn = null ;//12 цифр
	@FormField
	@Getter	@Setter
	private BigInteger pensionInsurance = null ;//? цифр
	
	
	
	@Getter	@Setter
	private Discipline[] disciplines;
	@Getter	@Setter
	private Group[] groups;
	@Getter	@Setter
	private Discipline discToAdd;
	@Getter	@Setter
	private Group groupToAdd;
	
	
	
	
	
	
	public List<Discipline> getDisciplineList(Teacher teacher) {
		return teacher != null ? bo.getDisciplineList(teacher) : null;
	}
	public List<Group> getGroupList(Teacher teacher) {
		return teacher != null ? bo.getGroupList(teacher) : null;
	}
	public List<Discipline> getAllDisciplines(Teacher teacher){
		return bo.getAllDisciplines(teacher);
	}
	public List<Group> getAllGroups(Teacher teacher){
		return bo.getAllGroups(teacher);
	}
	
	public String addDiscipline(Teacher teacher) {
		teacher.getDisciplines().add(getDiscToAdd());
		update(teacher);
		setDiscToAdd(null);
		return null;
	}
	public String addGroup(Teacher teacher) {
		teacher.getGroups().add(getGroupToAdd());
		update(teacher);
		setGroupToAdd(null);
		return null;
	}
	
	
	public String delGroup(Teacher teacher, Group group) {		
		teacher.getGroups().remove(group);
		update(teacher);
		return null;
	}
	public String delDiscipline(Teacher teacher, Discipline discipline) {		
		teacher.getDisciplines().remove(discipline);	
		update(teacher);
		return null;
	}
	
	
	
}
