package ru.nick.bo.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import ru.nick.bo.ModuleQuestionAnswerBo;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.bo.TestBo;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Test;
/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за тесты и реализует 
 * {@link TestBo}
 * @author NovikovNick
 *
 */
@Named("testBo")
public class TestBoImpl extends AbstaractBusinessObject<Test> implements TestBo{

	private final static String EMPTY_TEST_TITLE = "Новый тест";
	
	@Inject
	@Named("testDao")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudDao<Test> dao;

	@Inject
	@Named("mqaBo")
	@Getter(AccessLevel.PROTECTED)
	private ModuleQuestionAnswerBo  mqa;
	
	@Inject
	@Named("moduleBo")
	@Getter(AccessLevel.PROTECTED)
	private SimpleCrudBusinessObject<Module> m;
	
	@Override
	public void add(Test entity) {
		entity.setDate(getCurrentDate());
		super.add(entity);
	}

	@Override
	public List<Module> getAllModules() {
		return mqa.getAllModules();
	}

	@Override
	public void addTest() {
		Test test  = new Test();
		test.setTitle(EMPTY_TEST_TITLE);
		add(test);		
	}

	@Override
	public List<Question> getModuleQuestions(Module module, Test activeTest) {
		List<Question> res = mqa.getModuleQuestions(module);
		res.removeAll(activeTest.getQuestions());
		return res;
	}

	@Override
	public List<Question> getTestQuestions(Test activeTest) {
		return asOrderList(activeTest.getQuestions());
	}

	@Override
	public Module refreshModule(Module newValue) {
		return m.getById(newValue.getId());
	}
	
	
}