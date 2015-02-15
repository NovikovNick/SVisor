package ru.nick.managedbean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;
import ru.nick.model.AcademicTitle;
import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

@Component("teacherBean")
@Scope("request")
public class TeacherBean {
	
	
	private static final String TEST = "test";
	
	@Inject
	@Named("teacherDao")
	private SimpleCrudDao<Teacher> dao;
	
	private long id;
	
	private String fstName = TEST;
	private String sndName = TEST;
	private String surname = TEST;
	
	private String login = TEST;
	private String password = TEST;
	
	private AcademicDegree degree;	
	private AcademicTitle title;
	
	private BigInteger inn = null ;//12 цифр
	private BigInteger pensionInsurance = null ;//? цифр
	
	private Discipline[] disciplines;
	private Group[] groups;
	
	private Discipline discToAdd;	
	private Group groupToAdd;
	
	
	List<Teacher> allTeacher;//dell?
	
	
	

	//************* Getters/Setters ********************//
	//*************       START     ********************//
	public Group getGroupToAdd() {return groupToAdd;}
	public void setGroupToAdd(Group groupToAdd) {this.groupToAdd = groupToAdd;}
	
	public Discipline getDiscToAdd() {return discToAdd;	}
	public void setDiscToAdd(Discipline discToAdd) {this.discToAdd = discToAdd;}
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getFstName() {return fstName;}
	public void setFstName(String fstName) {this.fstName = fstName;}
	
	public String getSndName() {return sndName;}
	public void setSndName(String sndName) {this.sndName = sndName;}
	
	public String getSurname() {return surname;}
	public void setSurname(String surname) {this.surname = surname;}
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public AcademicDegree getDegree() {return degree;}
	public void setDegree(AcademicDegree degree) {this.degree = degree;}
	
	public AcademicTitle getTitle() {return title;}
	public void setTitle(AcademicTitle title) {	this.title = title;	}
	
	public BigInteger getInn() {return inn;}
	public void setInn(BigInteger inn) {this.inn = inn;}
	
	public BigInteger getPensionInsurance() {return pensionInsurance;}
	public void setPensionInsurance(BigInteger pensionInsurance) {this.pensionInsurance = pensionInsurance;	}
	
	public Discipline[] getDisciplines() {return disciplines;}
	public void setDisciplines(Discipline[] disciplines) {	this.disciplines = disciplines;}
	
	public Group[] getGroups() {return groups;}
	public void setGroups(Group[] groups) {	this.groups = groups;}
	//*************        END      ********************//
	
	
	
	
	
	
	// ************* User's CRUD methods ****************//
	//*************       START     ********************//
	/** Create */
	public void add() {
		Teacher teacher = new Teacher();
		
		teacher.setId(getId());
		
		teacher.setFstName(getFstName());
		teacher.setSndName(getSndName());
		teacher.setSurname(getSurname());
		
		teacher.setDegree(getDegree());
		teacher.setTitle(getTitle());
		
		teacher.setLogin(getLogin());
		teacher.setPassword(getPassword());
		
		teacher.setInn(getInn());
		teacher.setPensionInsurance(getPensionInsurance());
		
		teacher.setDisciplines(new HashSet<Discipline>(Arrays.asList(getDisciplines())));
		teacher.setGroups(new HashSet<Group>(Arrays.asList(getGroups())));
		
		dao.add(teacher);
		clearForm();
		refreshTeacher();
	}
	
	/** Read */
	public List<Teacher> getAllTeacher() {
		if (allTeacher == null) {
			refreshTeacher();
		}
		return allTeacher;
	}
	
	/** Update */
	public String update(Teacher teacher) {
		dao.update(teacher);
		refreshTeacher();
		return null;
	}
	/** Update */
	public String delete(Teacher teacher) {
		dao.delete(teacher);
		refreshTeacher();
		return null;
	}
	//*************        END      ********************//
	
	private void refreshTeacher() {
		allTeacher = dao.findAll();
	}
	
	// *********** User's JSF-form methods **************//
	// *************      START      ********************//
	private void clearForm() {
		
		setId(0L);
		
		setFstName(TEST);
		setSndName(TEST);
		setSurname(TEST);
		
		setDegree(null);;
		setTitle(null);
		
		setLogin(TEST);
		setPassword(TEST);
		
		setInn(null);
		setPensionInsurance(null);
		
		
	}
	
	public List<Discipline> getDisciplineListCurrentTeacher(Teacher teacher) {
		return new ArrayList<Discipline>(teacher.getDisciplines());
	}
	public List<Group> getGroupListCurrentTeacher(Teacher teacher) {
		return new ArrayList<Group>(teacher.getGroups());
	}
	
	public String delDiscipline(Teacher teacher, Long disciplineId) {		
		teacher.delDiscipline(disciplineId);		
		update(teacher);
		return null;
	}
	public String addDiscipline(Teacher teacher) {
		teacher.getDisciplines().add(getDiscToAdd());
		update(teacher);
		setDiscToAdd(null);
		return null;
	}
	
	public String delGroup(Teacher teacher, Long groupId) {		
		teacher.delGroup(groupId);		
		update(teacher);
		return null;
	}
	public String addGroup(Teacher teacher) {
		teacher.getGroups().add(getGroupToAdd());
		update(teacher);
		setGroupToAdd(null);
		return null;
	}
	
	// *************        END        ********************//
	
	
}
