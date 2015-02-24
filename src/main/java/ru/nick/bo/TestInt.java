package ru.nick.bo;

import java.util.List;

import ru.nick.model.Module;
import ru.nick.model.Question;
import ru.nick.model.Test;

public interface TestInt extends SimpleCrudBusinessObject<Test>{

	List<Module> getAllModules();

	void addTest();

	List<Question> getModuleQuestions(Module module, Test activeTest);

	List<Question> getTestQuestions(Test activeTest);

	Module refreshModule(Module newValue);

	
}
