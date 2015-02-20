package ru.nick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "speciality")
@NamedQuery(name = "Speciality.getAll", query = "SELECT s FROM Speciality s")
@EqualsAndHashCode(exclude = "title")
@ToString(exclude="id")
public class Speciality implements Identifiable{


	@Id
	@Getter @Setter
	private Long id;	
	@Column
	@Getter @Setter
	private String title;

}
