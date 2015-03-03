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

/**
 * Ответ на определенный вопрос {@link Question}
 * 
 * @author NovikovNick
 * @see Question
 * @see Module
 */
@Entity
@Table(name = "answer")
@NamedQuery(name = "Answer.getByQuestionId", query = "SELECT a FROM Answer a "
        + "JOIN a.ownerQuestion q " + "WHERE q.id = :idQuestion ")
public class Answer extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @JoinColumn(name = "content")
    private String content;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question ownerQuestion;
    @Getter
    @Setter
    @JoinColumn(name = "correct")
    private boolean correct;

}
