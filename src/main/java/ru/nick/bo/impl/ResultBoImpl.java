package ru.nick.bo.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.nick.bo.ResultBo;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Question;
import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.Test;

/**
 * Класс-наследник {@link AbstaractBusinessObject}. Отвечает за результаты
 * проxождения теста. Реализует функцинал {@link ResultBo}
 * 
 * @author NovikovNick
 *
 */
@Named("resultBo")
public class ResultBoImpl extends AbstaractBusinessObject<Result> implements ResultBo {

    @Inject
    @Named("resultDao")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudDao<Result> dao;

    private @Getter @Setter Student student;
    private @Getter @Setter int currentAttempt;
    private @Getter @Setter Map<Question, Boolean> result;
    private @Getter @Setter Test test;

    private final double MAX_RES = 100;

    @PostConstruct
    private void init() {
        result = new LinkedHashMap<Question, Boolean>();
    }

    public void done() {
        Result result = new Result();
        result.setAttempt(getCurrentAttempt());
        result.setStudent(getStudent());
        result.setResult(calculate(getResult()));
        result.setTest(getTest());
        dao.add(result);
    }

    private int calculate(Map<Question, Boolean> result2) {
        int max = result2.size();
        int res = 0;
        for (Boolean correct : result2.values()) {
            if (correct.booleanValue())
                res++;
        }
        return (int) (res == 0 ? 0 : res / (max / MAX_RES));
    }

}
