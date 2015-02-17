package ru.nick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "speciality")
@NamedQuery(name = "Speciality.getAll", query = "SELECT s FROM Speciality s")
public class Speciality  implements Identifiable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column
	private String title;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		Speciality that = (Speciality) obj;

		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}
}
