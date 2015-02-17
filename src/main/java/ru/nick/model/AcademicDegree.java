package ru.nick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "academicDegree")
@NamedQuery(name = "AcademicDegree.getAll", query = "SELECT ad FROM AcademicDegree ad")
public class AcademicDegree extends AbstractPersistable<Long> implements Identifiable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String fullDegree;
	@Column
	private String reducDegree;
	
	public Long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	
	public String getFullDegree() {		return fullDegree;	}
	public void setFullDegree(String fullDegree) {		this.fullDegree = fullDegree;	}
	
	public String getReducDegree() {		return reducDegree;	}
	public void setReducDegree(String reducDegree) {		this.reducDegree = reducDegree;	}
	
	
}
