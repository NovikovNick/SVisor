package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.ResultBo;
import ru.nick.model.Result;

@Component("resultBean")
@Scope("request")
public class ResultBean extends AbstarctManagedBean<Result> {

	@Inject
	@Named("resultBo")
	@Getter(AccessLevel.PROTECTED)
	private ResultBo bo;
	
	

	
	
}
