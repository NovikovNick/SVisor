package ru.nick.bo.testing;

import java.util.Map;

import lombok.Setter;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

public class ResultBuilder {

    private Student student;
    private int currentAttempt;
    private Map<Question, Answer> result;
    private TestAssign assignment;

    // TODO:In future this resolve about assigment
    private @Setter Strategy strategy = Strategy.SIMPLE;

    public ResultBuilder(Student student, int currentAttempt, Map<Question, Answer> result,
            TestAssign assignment) {
        this.student = student;
        this.currentAttempt = currentAttempt;
        this.result = result;
        this.assignment = assignment;
    }

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

    public Result buildResult() {
        Result res = new Result();
        res.setAttempt(currentAttempt);
        res.setStudent(student);

        res.setTest(assignment.getTest());

        res.setResult(strategy.calculate(this.result));
        return res;
    }

}
