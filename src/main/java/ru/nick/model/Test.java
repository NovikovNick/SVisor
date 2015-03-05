package ru.nick.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Совокупность определенных вопросов {@link Question} из нескольких модулей
 * {@link Module}
 * 
 * @author NovikovNick
 *
 */
@Entity
@Table(name = "test")
@NamedQuery(name = "Test.getAll", query = "SELECT t FROM Test t")
public class Test extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter Long id;
    @Column
    private @Getter @Setter String title;
    @Column(name = "_date")
    private @Getter @Setter Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_question", 
        joinColumns = @JoinColumn(name = "id_test", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_question", referencedColumnName = "id"))
    private @Getter @Setter Set<Question> questions;

}
