package ru.nick.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "answer")
@NamedQuery(name = "Answer.getByQuestionId",
query = "SELECT a FROM Answer a "
		+ "JOIN a.ownerQuestion q "
		+ "WHERE q.id = :idQuestion ")
public class Answer extends AbstractPersistable<Long>{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "id_question")
	private Question ownerQuestion;

	@JoinColumn(name = "correct")
	private boolean correct;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	public Question getOwnerQuestion() {return ownerQuestion;}
	public void setOwnerQuestion(Question ownerQuestion) {this.ownerQuestion = ownerQuestion;}
	
	public boolean isCorrect() {return correct;}
	public void setCorrect(boolean correct) {this.correct = correct;}

}

