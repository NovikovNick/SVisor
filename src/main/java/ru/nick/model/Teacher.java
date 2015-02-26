package ru.nick.model;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Класс содержит всю информацию про преподавателя, включая -  свою ученую степень 
 * {@link AcademicDegree} и ученое звание {@link AcademicTitle}, списки дисциплин
 *  {@link Discipline} и групп {@link Group}
 * @author NovikovNick
 *
 */
@Entity
@Table(name = "teacher")
@NamedQuery(name = "Teacher.getAll", query = "SELECT t from Teacher t")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "surname", "id" })
public class Teacher implements Identifiable {

	@Id
	private @Setter @Getter Long id;
	private @Setter @Getter String fstName;
	private @Setter @Getter String sndName;
	private @Setter @Getter String surname;
	private @Setter @Getter String login;
	private @Setter @Getter String password;

	@ManyToOne
	@JoinColumn(name = "id_academicDegree")
	private @Setter @Getter AcademicDegree degree;

	@ManyToOne
	@JoinColumn(name = "id_academicTitle")
	private @Setter @Getter AcademicTitle title;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_discipline", joinColumns = @JoinColumn(name = "id_teacher", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_discipline", referencedColumnName = "id"))
	private @Setter @Getter Set<Discipline> disciplines;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_groups", joinColumns = @JoinColumn(name = "id_teacher", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id"))
	private @Setter @Getter Set<Group> groups;

	private @Setter @Getter BigInteger inn;// 12 цифр
	private @Setter @Getter BigInteger pensionInsurance;// ? цифр

}
