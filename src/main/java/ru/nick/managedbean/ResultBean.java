package ru.nick.managedbean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.impl.ResultBO;
import ru.nick.model.Result;

@Component("resultBean")
@Scope("request")
public class ResultBean {

	@Inject
	@Named("ResultBO")
	private ResultBO resultBo;

	public List<Result> findAll() {
		return resultBo.findAll();
	}
	
	
}
