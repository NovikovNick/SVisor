package ru.nick.bo.testing;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.nick.bo.impl.AbstaractBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за результаты
 * проxождения теста. Реализует функцинал {@link ResultBo}
 * 
 * @author NovikovNick
 *
 */
@Named("resultBo")
public class ResultParser extends AbstaractBusinessObject<Result> {

    @Inject
    @Named("resultDao")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudDao<Result> dao;

    private @Getter @Setter Student student;
    private @Getter @Setter int currentAttempt;
    private @Getter @Setter Map<Question, Answer> result;
    private @Getter @Setter TestAssign assignment;
    private @Getter @Setter Strategy strategy;

    public enum Strategy implements CalculateStrategy {
        SIMPLE {
            @Override
            public int calculate(Map<Question, Answer> result) {
                int max = result.size();
                int res = 0;
                for (Answer answer : result.values()) {
                    if (answer != null) {
                        if (answer.isCorrect()) {
                            res++;
                        }
                    }
                }
                return (int) (res == 0 ? 0 : res / (max / MAX_RESULT));
            }
        };
        private static final float MAX_RESULT = 100;
    }

    public void done() {
        Result result = new Result();
        result.setAttempt(getCurrentAttempt());
        result.setStudent(getStudent());
        result.setResult(strategy.calculate(getResult()));
        result.setTest(assignment.getTest());
        dao.add(result);
    }

}
