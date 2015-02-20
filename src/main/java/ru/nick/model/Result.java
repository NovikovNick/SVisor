package ru.nick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "result")
@NamedQuery(name = "Result.getAll", query = "SELECT r FROM Result r ")
public class Result  extends AbstractPersistable<Long>  implements Identifiable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter Long id;
	private @Getter @Setter int attempt;
	private @Getter @Setter int result;
	@ManyToOne
	@JoinColumn(name = "id_student")
	private @Getter @Setter Student student;
	@ManyToOne
	@JoinColumn(name = "id_test")
	private @Getter @Setter Test test;
	
}
