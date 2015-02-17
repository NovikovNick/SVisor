package ru.nick.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "discipline")
@NamedQuery(name = "Discipline.getAll", query = "SELECT d FROM Discipline d")
public class Discipline extends AbstractPersistable<Long> implements Identifiable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(mappedBy = "disciplines")//, cascade = CascadeType.MERGE
    private	Set<Teacher> teachers;
	
	@Column
	private String title;
	
	public Long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public Set<Teacher> getTeachers() {return teachers;}
	public void setTeachers(Set<Teacher> teachers) {this.teachers = teachers;}
	
	
}
