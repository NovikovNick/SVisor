package ru.nick.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


@Entity
@Table(name = "test")
@NamedQuery(name = "Test.getAll", query = "SELECT t FROM Test t")
public class Test extends AbstractPersistable<Long>  implements Identifiable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter Long id;
	@Column
	private @Getter @Setter String title;
	@Column(name = "_date")
	private @Getter @Setter Date date;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="test_question",
		joinColumns = @JoinColumn(name="id_test", referencedColumnName="id"),
	    inverseJoinColumns = @JoinColumn(name="id_question", referencedColumnName="id"))
	private @Getter @Setter Set<Question> questions;
	
	
	public List<Question> tmpQ() {
		if (questions == null) {
			return new ArrayList<Question>();
		}
		List<Question> res = new ArrayList<Question>(questions);
		Collections.sort(res, new Comparator<Question>() {

			@Override
			public int compare(Question o1, Question o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return res;
	}
}
