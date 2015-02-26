package ru.nick.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;
/**
 * Назначение на тест {@link Test}, определяющее основные условия выполнения и список групп {@link Group}
 * @author NovikovNick
 *
 */
@Entity
@Table(name = "testassign")
@NamedQuery(name = "TestAssign.getAll", query = "SELECT tA from TestAssign tA")
public class TestAssign  extends AbstractPersistable<Long>  implements Identifiable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter  Long id;
	private @Getter @Setter String title;
	private @Getter @Setter String description;
	private @Getter @Setter  Date date_start;
	private @Getter @Setter  Date date_end;
	private @Getter @Setter  int passing_score;
	private @Getter @Setter  int completion_time;
	private @Getter @Setter  int attempts;	
	
	
	@ManyToOne
	@JoinColumn(name = "author")
	private @Getter @Setter  Teacher author;
	
	
	@ManyToOne
	@JoinColumn(name = "id_test")
	private @Getter @Setter  Test test;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="testassign_groups",
			joinColumns = @JoinColumn(name="id_testassign", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_group", referencedColumnName="id"))
	private @Getter @Setter  Set<Group> groups;
	
	
	//BO?
	public List<Group> tmpG() {
		return new ArrayList<>(groups);
	}
}
