package ru.nick.model;

import java.sql.Date;
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

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Определенная тема со своим списоком вопросов {@link Question}
 * 
 * @author NovikovNick
 *
 */
@Entity
@Table(name = "module")
@NamedQuery(name = "Module.getAll", query = "SELECT m FROM Module m")
public class Module extends AbstractPersistable<Long> implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @Column
    private String title;
    @Getter
    @Setter
    @Column(name = "_date")
    private Date date;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerModule", fetch = FetchType.EAGER)
    Set<Question> questions;

}
