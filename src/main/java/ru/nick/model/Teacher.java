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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@NamedQuery(name = "Teacher.getAll", query = "SELECT t from Teacher t")
@EqualsAndHashCode(exclude = {
		"fstName", "sndName", "login", "password", "degree",
		"title", "disciplines", "groups", "inn", "pensionInsurance"})
@ToString(exclude="id")
public class Teacher implements Identifiable{
	

	@Id
	private @Setter @Getter Long id;
	private @Setter @Getter String fstName;
	private @Setter @Getter String sndName;
	private @Setter @Getter String surname;
	private @Setter @Getter String login;
	private @Setter @Getter String password;
	
	@ManyToOne
	@JoinColumn(name = "id_academicDegree")
	private @Setter @Getter AcademicDegree degree;
	
	@ManyToOne
	@JoinColumn(name = "id_academicTitle")
	private @Setter @Getter AcademicTitle title;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="teacher_discipline",
			joinColumns = @JoinColumn(name="id_teacher", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_discipline", referencedColumnName="id"))
	private @Setter @Getter Set<Discipline> disciplines;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="teacher_groups",
			joinColumns = @JoinColumn(name="id_teacher", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_group", referencedColumnName="id"))
	private @Setter @Getter Set<Group> groups;
	
	
	private @Setter @Getter BigInteger inn;//12 цифр
	private @Setter @Getter BigInteger pensionInsurance;//? цифр
	
		
	
	
	//*** *** *** *** *** BO START *** *** *** *** *** *** 
	
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
	public List<Discipline> tmpD(){
		return new ArrayList<>(disciplines);
	}
	public List<Group> tmpG(){
		return new ArrayList<>(groups);
	}
	//*** *** *** *** *** BO  END  *** *** *** *** *** *** 
}
