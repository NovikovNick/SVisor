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
 * Сущность системы, представляющая собой ученую степень 
 * 
 * @author NovikovNick
 * @see Identifiable
 * @see Teacher
 */
@Entity
@Table(name = "academicDegree")
@NamedQuery(name = "AcademicDegree.getAll", query = "SELECT ad FROM AcademicDegree ad")
public class AcademicDegree extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @Column
    private String fullDegree;
    @Getter
    @Setter
    @Column
    private String reducDegree;

}
