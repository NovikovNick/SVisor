package ru.nick.bo;

import java.util.List;

import ru.nick.model.Answer;
import ru.nick.model.Module;
import ru.nick.model.Question;

/**
 * Интерфейс бизнес слоя для модулей, вопросов и ответов
 * 
 * @author NovikovNick
 *
 */
public interface ModuleQuestionAnswerBo {

    public void addModule();

    public Module addQuestion(Module module);

    public Module addAnswer(Question question);

    public List<Module> getAllModules();

    public List<Question> getModuleQuestions(Module module);

    public List<Answer> getQuestionAnswer(Question question);

    public Module update(Module module);

    public Question update(Question question);

    public Answer update(Answer answer);

    public void delete(Module module);

    public void delete(Question question);

    public void delete(Answer answer);

}
