package ru.nick.model;

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

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Вопрос со списком возможных вариантов ответа {@link Answer}
 * 
 * @author NovikovNick
 *
 */
@Entity
@Table(name = "question")
@NamedQuery(name = "Question.getByModuleId", query = "SELECT q FROM Question q "
        + "JOIN q.ownerModule m " + "WHERE m.id = ?1 ")
public class Question extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column
    private String content;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Difficult difficult;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module ownerModule;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "questions")
    private Set<Test> tests;// Need?

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerQuestion", fetch = FetchType.EAGER)
    Set<Answer> answers;

    public enum Difficult {
        EASY, MEDIUM, HARD
    }

}
