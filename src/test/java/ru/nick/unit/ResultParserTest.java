package ru.nick.unit;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.nick.bo.testing.ResultParser;
import ru.nick.bo.testing.ResultParser.Strategy;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", "/spring-jpa-test.xml",
        "/spring-cashe-test.xml" })
public class ResultParserTest extends Assert {

    @Mock
    SimpleCrudDao<Result> dao;

    @Inject
    @Named("resultBo")
    public ResultParser parser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        try {
            Field dao = ResultParser.class.getDeclaredField("dao");
            dao.setAccessible(true);
            dao.set(parser, this.dao);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleStrategy() {

        parser.setStrategy(Strategy.SIMPLE);
        for (int i = 0; i < 100; i++) {
            assertEquals(
                    i,
                    parser.getStrategy().calculate(
                            createResultMap(new HashMap<Question, Answer>(), i)));
        }

        for (int i = 0; i < 100; i++) {
            assertEquals(
                    i,
                    parser.getStrategy().calculate(
                            createHalfEmptyResultMap(new HashMap<Question, Answer>(), i)));
        }

    }

    private Map<Question, Answer> createResultMap(Map<Question, Answer> map, int count) {

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

    private Map<Question, Answer> createHalfEmptyResultMap(Map<Question, Answer> map, int count) {

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
