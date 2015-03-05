package ru.nick.bo;

import java.util.Map;

import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.Test;

/**
 * Интерфейс бизнес слоя для результата проxождения теста
 * 
 * @author NovikovNick
 *
 */
public interface ResultBo extends SimpleCrudBusinessObject<Result> {

    void setCurrentAttempt(int currentAttempt);

    void setStudent(Student student);

    void setTest(Test test);

    void done();

    Map<String, Boolean> getResult();

}
