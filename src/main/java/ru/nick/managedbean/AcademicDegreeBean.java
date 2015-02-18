package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

@Component("academicDegreeBean")
@Scope("request")
public class AcademicDegreeBean extends AbstarctManagedBean<AcademicDegree> {

	@Inject
	@Named("academicDegreeDao")
	private SimpleCrudDao<AcademicDegree> dao;

	
	@FormField
	private @Getter @Setter String fullDegree;
	
	@FormField
	private @Getter @Setter String reducDegree;
	
	
	@Override
	protected Class<AcademicDegree> getGenericClass() {
		return AcademicDegree.class;
	}
	@Override
	protected SimpleCrudDao<AcademicDegree> getDao() {return dao;}
	
		
	

}
