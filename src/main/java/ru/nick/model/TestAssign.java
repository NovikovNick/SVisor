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

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "testassign")
@NamedQuery(name = "TestAssign.getAll", query = "SELECT tA from TestAssign tA")
public class TestAssign  extends AbstractPersistable<Long>  implements Identifiable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private Date date_start;
	private Date date_end;
	private int passing_score;
	private int completion_time;
	private int attempts;	
	@ManyToOne
	@JoinColumn(name = "author")
	private Teacher author;
	@ManyToOne
	@JoinColumn(name = "id_test")
	private Test test;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="testassign_groups",
			joinColumns = @JoinColumn(name="id_testassign", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="id_group", referencedColumnName="id"))
	private Set<Group> groups;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public Date getDate_start() {return date_start;}
	public void setDate_start(Date date_start) {this.date_start = date_start;}
	
	public Date getDate_end() {return date_end;}
	public void setDate_end(Date date_end) {this.date_end = date_end;}
	
	public int getPassing_score() {return passing_score;}
	public void setPassing_score(int passing_score) {this.passing_score = passing_score;}
	
	public int getCompletion_time() {return completion_time;}
	public void setCompletion_time(int completion_time) {this.completion_time = completion_time;}
	
	public int getAttempts() {return attempts;}
	public void setAttempts(int attempts) {this.attempts = attempts;}
	
	public Teacher getAuthor() {return author;}
	public void setAuthor(Teacher author) {this.author = author;}
	
	public Test getTest() {return test;}
	public void setTest(Test test) {this.test = test;}
	
	public Set<Group> getGroups() {return groups;}
	public void setGroups(Set<Group> groups) {this.groups = groups;}
	
	//BO?
	public List<Group> tmpG() {
		return new ArrayList<>(groups);
	}
}
