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

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "groups")
@NamedQuery(name = "Group.getAll", query = "SELECT g FROM Group g")
public class Group extends AbstractPersistable<Long> implements Identifiable{

	private static final long serialVersionUID = 1L;
	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Getter @Setter
	@Column
	private String title;
	@Getter @Setter
	@Column
	private int course;
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "id_speciality")
	private Speciality speciality;
	@Getter @Setter
	@ManyToMany(mappedBy = "groups")
    private	Set<Teacher> teachers;
	@Getter @Setter
	@ManyToMany(mappedBy = "groups")
    private	Set<TestAssign> testAssign;
	
}
