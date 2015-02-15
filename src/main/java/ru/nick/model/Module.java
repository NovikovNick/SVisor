package ru.nick.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "module")
@NamedQuery(name = "Module.getAll", query = "SELECT m FROM Module m")
public class Module extends AbstractPersistable<Long>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String title;
	@Column(name = "_date")
	private Date date;
	
	@OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "ownerModule", fetch=FetchType.EAGER)
	Set<Question> questions;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public Date getDate() {return date;}	
	public void setDate(Date date) {this.date = date;}
	
	public Set<Question> getQuestions() {return questions;}
	public void setQuestions(Set<Question> questions) {this.questions = questions;}
	
	public List<Question> tmpQ() {
		if (questions == null) {
			return new ArrayList<Question>();
		}
		List<Question> res = new ArrayList<Question>(questions);
		Collections.sort(res, new Comparator<Question>() {

			@Override
			public int compare(Question o1, Question o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return res;
	}
}
