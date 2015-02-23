package ru.nick.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(name = "Student.getAll", query = "SELECT s from Student s"),
	@NamedQuery(name = "Student.getByLoginPassword", 
	query = "SELECT s from Student s WHERE s.login = ?1 AND s.password = ?2")
})
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"surname", "id"})
public class Student implements Identifiable{

	@Id
	private @Setter @Getter Long id;	
	private @Setter @Getter String fstName;
	private @Setter @Getter String sndName;
	private @Setter @Getter String surname;
	private @Setter @Getter String login;
	private @Setter @Getter String password;
	
	@ManyToOne
	@JoinColumn(name = "id_group")
	private @Setter @Getter Group group;

}
