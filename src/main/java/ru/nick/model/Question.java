package ru.nick.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "question")
@NamedQuery(name = "Question.getByModuleId", 
query = "SELECT q FROM Question q "
		+ "JOIN q.ownerModule m "
		+ "WHERE m.id = ?1 ")
public class Question extends AbstractPersistable<Long>{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column
	private String content;

	@Enumerated(EnumType.STRING)
	private Difficult difficult;
	public enum Difficult {EASY, MEDIUM, HARD}
	
	@ManyToOne
	@JoinColumn(name = "id_module")
	private Module ownerModule;
	
	@ManyToMany(mappedBy = "questions")
    private	Set<Test> tests;//Need?
	
	
	
	

	@OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "ownerQuestion", fetch=FetchType.EAGER)
	Set<Answer> answers;

	public Long getId() {return id;	}
	public void setId(Long id) {this.id = id;}

	public String getContent() {return content;	}
	public void setContent(String content) {this.content = content;}

	public Difficult getDifficult() {return difficult;}
	public void setDifficult(Difficult difficult) {this.difficult = difficult;}

	public Module getOwnerModule() {return ownerModule;	}
	public void setOwnerModule(Module ownerModule) {this.ownerModule = ownerModule;	}
	
	public Set<Answer> getAnswers() {return answers;}
	public void setAnswers(Set<Answer> answers) {this.answers = answers;}
	
	public Set<Test> getTests() {return tests;}
	public void setTests(Set<Test> tests) {this.tests = tests;}
	
	//BO?
	public List<Answer> tmpA(){
		if (answers == null) {
			return new ArrayList<Answer>();
		}
		List<Answer> res = new ArrayList<Answer>(answers);
		Collections.sort(res, new Comparator<Answer>() {

			@Override
			public int compare(Answer o1, Answer o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return res;
	}
	
	
}
