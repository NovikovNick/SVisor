package ru.nick.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "teacher")
@NamedQuery(name = "Teacher.getAll", query = "SELECT t from Teacher t")
public class Teacher implements Identifiable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String fstName;
	private String sndName;
	private String surname;
	
	private String login;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_academicDegree")
	private AcademicDegree degree;
	
	@ManyToOne
	@JoinColumn(name = "id_academicTitle")
	private AcademicTitle title;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="teacher_discipline",
			joinColumns = @JoinColumn(name="id_teacher", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_discipline", referencedColumnName="id"))
	private Set<Discipline> disciplines;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="teacher_groups",
			joinColumns = @JoinColumn(name="id_teacher", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_group", referencedColumnName="id"))
	private Set<Group> groups;
	
	
	private BigInteger inn;//12 цифр
	private BigInteger pensionInsurance;//? цифр
	
	
	public Set<Group> getGroups() {return groups;}
	public void setGroups(Set<Group> groups) {this.groups = groups;}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getFstName() {
		return fstName;
	}
	public void setFstName(String fstName) {
		this.fstName = fstName;
	}
	public String getSndName() {
		return sndName;
	}
	public void setSndName(String sndName) {
		this.sndName = sndName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AcademicDegree getDegree() {
		return degree;
	}
	public void setDegree(AcademicDegree degree) {
		this.degree = degree;
	}
	public AcademicTitle getTitle() {
		return title;
	}
	public void setTitle(AcademicTitle title) {
		this.title = title;
	}
	public BigInteger getInn() {
		return inn;
	}
	public void setInn(BigInteger inn) {
		this.inn = inn;
	}
	public BigInteger getPensionInsurance() {
		return pensionInsurance;
	}
	public void setPensionInsurance(BigInteger pensionInsurance) {
		this.pensionInsurance = pensionInsurance;
	}
	

	
	//BO? 
	public void delDiscipline(Long disciplineId) {
		for (Discipline discipline : disciplines) {
			if (disciplineId.equals(discipline.getId())) {
				disciplines.remove(discipline);
				return;
			}
		}
	}
	public void delGroup(Long groupId) {
		for (Group group : groups) {
			if (groupId.equals(group.getId())) {
				groups.remove(group);
				return;
			}
		}
	}
	//BO?
	public List<Discipline> tmpD(){
		return new ArrayList<>(disciplines);
	}
	//BO?
	public List<Group> tmpG(){
		return new ArrayList<>(groups);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		Teacher that = (Teacher) obj;

		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}
	
}
