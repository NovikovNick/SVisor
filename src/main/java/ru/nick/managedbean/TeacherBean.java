package ru.nick.managedbean;

import java.math.BigInteger;
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

import ru.nick.bo.TeacherBo;
import ru.nick.model.AcademicDegree;
import ru.nick.model.AcademicTitle;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

/**
 * Класс-наследник {@link AbstarctManagedBean}. Отвечает за преподавателей
 * @author NovikovNick
 *
 */
@Component("teacherBean")
@Scope("request")
public class TeacherBean extends AbstarctManagedBean<Teacher> {

	@Inject
	@Named("teacherBo")
	@Getter(AccessLevel.PROTECTED)
	private TeacherBo bo;
	
	private static final String TEST = "test";
	
	@FormField
	@Getter	@Setter
	private Long id;
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
	
	@FormField
	@Getter	@Setter
	private Discipline[] disciplines;	
	@FormField
	@Getter	@Setter
	private Group[] groups;
	
	@Getter	
	private Discipline discToAdd;
	@Getter
	private Group groupToAdd;
	
	
	@Override
	public String add() {
		Teacher teacher = new Teacher();		
		fillFields(teacher);
		teacher.setDisciplines(new HashSet<Discipline>(Arrays.asList(disciplines)));
		teacher.setGroups(new HashSet<Group>(Arrays.asList(groups)));
		getBo().add(teacher);
		clearForm();
		refresh();
		return null;
	}
	//Установка 1 раз, а после addDiscipline(Teacher teacher) обнуление
	public void setDiscToAdd(Discipline discToAdd) {
		if (this.discToAdd == null) {
			this.discToAdd = discToAdd;
		}
	}
	//Установка 1 раз, а после addGroup(Teacher teacher) обнуление
	public void setGroupToAdd(Group groupToAdd) {
		if (this.groupToAdd == null) {
			this.groupToAdd = groupToAdd;
		}
	}
	
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
	
	//BO?
	public String addDiscipline(Teacher teacher) {

		teacher.getDisciplines().add(getDiscToAdd());
		update(teacher);
		discToAdd = null;
		return null;
		
	}
	//BO?
	public String addGroup(Teacher teacher) {
		
		teacher.getGroups().add(getGroupToAdd());
		update(teacher);
		groupToAdd = null;
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
