package ru.nick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Сущность системы, представляющая собой ученое звание 
 * 
 * @author NovikovNick
 * @see Identifiable
 * @see Teacher
 * 
 */
@Entity
@Table(name = "academicTitle")
@NamedQuery(name = "AcademicTitle.getAll", query = "SELECT at FROM AcademicTitle at")
public class AcademicTitle extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @Column
    private String fullTitle;
    @Getter
    @Setter
    @Column
    private String reducTitle;

}
