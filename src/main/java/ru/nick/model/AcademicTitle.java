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
@Table(name = "academicTitle")
@NamedQuery(name = "AcademicTitle.getAll", query = "SELECT at FROM AcademicTitle at")
public class AcademicTitle  extends AbstractPersistable<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String fullTitle;
	@Column
	private String reducTitle;
	
	public Long getId() {		return id;	}
	public void setId(long id) {		this.id = id;	}
	
	public String getFullTitle() {		return fullTitle;	}
	public void setFullTitle(String fullTitle) {		this.fullTitle = fullTitle;	}
	
	public String getReducTitle() {		return reducTitle;	}
	public void setReducTitle(String reducTitle) {		this.reducTitle = reducTitle;	}
	
	
	
	
}
