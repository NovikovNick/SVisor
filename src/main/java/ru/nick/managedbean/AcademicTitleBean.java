package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.AcademicTitle;

@Component("academicTitleBean")
@Scope("request")
public class AcademicTitleBean extends AbstarctManagedBean<AcademicTitle> {

	@Inject
	@Named("academicTitleBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<AcademicTitle> bo;
	
	@FormField
	@Getter	@Setter
	private String fullTitle;
	
	@FormField
	@Getter	@Setter
	private String reducTitle;
	
	
}
