package ru.nick.unit;

import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

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

import ru.nick.bo.testing.TestProvider;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", "/spring-jpa-test.xml",
        "/spring-cashe-test.xml" })
public class TestProviderTest extends Assert {

    @Mock
    TestAssign assignment;
    @Mock
    ru.nick.model.Test test;
    @Mock
    Student student;
    @Inject
    @Named("testProvider")
    public TestProvider provider;

    private long answersId = 0;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Set<Question> set = new HashSet<>();

        for (long i = 1; i <= 6; i++) {
            Question q = new Question();
            q.setId(i);
            q.setContent("question " + i);
            set.add(q);
        }

        when(assignment.getTest()).thenReturn(test);
        when(test.getQuestions()).thenReturn(set);
        when(test.getTitle()).thenReturn("Testing");
        when(student.getId()).thenReturn(1130030028L);

    }

    @Test
    public void cursorNext() {

        provider.setStudent(student);
        provider.start(assignment);
        checkCursorPosition(0);
        answer(true);
        checkCursorPosition(1);
        answer(3, true);
        checkCursorPosition(4);
        answer(true);
        checkCursorPosition(5);
        answer(true);
        checkCursorPosition(1);
        answer(true);
        checkCursorPosition(2);
        answer(1, false);
        checkCursorPosition(2);
        answer(true);
        checkCursorPosition(2);
        answer(true);
        checkCursorPosition(2);

    }

    private void checkCursorPosition(int l) {
        try {
            Field cursor = TestProvider.class.getDeclaredField("cursor");
            cursor.setAccessible(true);
            assertEquals(l, cursor.get(provider));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void answer(int cursor, boolean correct) {
        provider.getQuestion(cursor);
        provider.answer(genereteAnswer(correct));
    }

    private void answer(boolean correct) {
        provider.getQuestion();
        provider.answer(genereteAnswer(correct));
    }

    private Answer genereteAnswer(boolean correct) {
        Answer answer = new Answer();
        answer.setId(answersId);
        answer.setContent("answer " + answersId++);
        answer.setCorrect(correct);
        return answer;
    }

}
