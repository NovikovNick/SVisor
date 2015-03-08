package ru.nick.unit;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.nick.bo.testing.ResultBuilder;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

public class ResultBuilderTest extends Assert {

    @Mock
    TestAssign assignment;

    @Mock
    Student student;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simpleStrategy() {

        for (int i = 0; i < 100; i++) {
            Map<Question, Answer> map = resultMap(new HashMap<Question, Answer>(), i);
            int res = new ResultBuilder(student, 0, map, assignment).buildResult().getResult();
            assertEquals(i, res);
        }

        for (int i = 0; i < 100; i++) {
            Map<Question, Answer> map = halfEmptyResultMap(new HashMap<Question, Answer>(), i);
            int res = new ResultBuilder(student, 0, map, assignment).buildResult().getResult();
            assertEquals(i, res);
        }

    }

    private Map<Question, Answer> resultMap(Map<Question, Answer> map, int count) {

        int currentCountOfTrueAnswer = 0;
        for (long i = 0; i < 100; i++) {
            Question q = new Question();
            q.setId(i);
            Answer a = new Answer();
            a.setId(i);
            if (count > currentCountOfTrueAnswer) {
                a.setCorrect(true);
            } else {
                a.setCorrect(false);
            }
            map.put(q, a);
            currentCountOfTrueAnswer++;
        }
        return map;
    }

    private Map<Question, Answer> halfEmptyResultMap(Map<Question, Answer> map, int count) {

        int currentCountOfTrueAnswer = 0;
        for (long i = 0; i < 100; i++) {
            Question q = new Question();
            q.setId(i);
            Answer a = new Answer();
            a.setId(i);
            if (count > currentCountOfTrueAnswer) {
                a.setCorrect(true);
                map.put(q, a);
            } else {
                map.put(q, null);
            }

            currentCountOfTrueAnswer++;
        }
        return map;
    }

}
