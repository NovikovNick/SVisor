package ru.nick.bo.testing;

import java.util.Map;

import ru.nick.model.Answer;
import ru.nick.model.Question;

public interface CalculateStrategy {
    public int calculate(Map<Question, Answer> result);
}
