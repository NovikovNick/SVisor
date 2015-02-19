package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.AcademicDegree;

@Component("academicDegreeBean")
@Scope("request")
public class AcademicDegreeBean extends AbstarctManagedBean<AcademicDegree> {

	@Inject
	@Named("academicDegreeBO")
	private SimpleCrudBusinessObject<AcademicDegree> bo;

	@FormField
	private @Getter @Setter String fullDegree;

	@FormField
	private @Getter @Setter String reducDegree;

	@Override
	protected Class<AcademicDegree> getGenericClass() {
		return AcademicDegree.class;
	}

	@Override
	protected SimpleCrudBusinessObject<AcademicDegree> getBo() {
		return bo;
	}

}
