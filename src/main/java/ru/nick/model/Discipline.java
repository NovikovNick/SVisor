package ru.nick.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Предмет, обязательный в изучении для выбранной студентом специальности.
 * 
 * @author NovikovNick
 * @see Teacher
 * @see Module
 */
@Entity
@Table(name = "discipline")
@NamedQuery(name = "Discipline.getAll", query = "SELECT d FROM Discipline d")
public class Discipline extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @ManyToMany(mappedBy = "disciplines")
    // , cascade = CascadeType.MERGE
    private Set<Teacher> teachers;
    @Getter
    @Setter
    @Column
    private String title;

}
