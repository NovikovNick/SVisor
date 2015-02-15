package ru.nick.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "groups")
@NamedQuery(name = "Group.getAll", query = "SELECT g FROM Group g")
public class Group extends AbstractPersistable<Long>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String title;
	@Column
	private int course;
	@ManyToOne
	@JoinColumn(name = "id_speciality")
	private Speciality speciality;
	
	@ManyToMany(mappedBy = "groups")
    private	Set<Teacher> teachers;
	
	@ManyToMany(mappedBy = "groups")
    private	Set<TestAssign> testAssign;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getCourse() {return course;}
	public void setCourse(int course) {this.course = course;}
	
	public Speciality getSpeciality() {return speciality;}
	public void setSpeciality(Speciality speciality) {this.speciality = speciality;}
	
	public Set<Teacher> getTeachers() {return teachers;}
	public void setTeachers(Set<Teacher> teachers) {this.teachers = teachers;}
	
	public Set<TestAssign> getTestAssign() {return testAssign;}
	public void setTestAssign(Set<TestAssign> testAssign) {this.testAssign = testAssign;}
}
