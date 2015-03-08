package ru.nick.bo.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Result;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

@Named("testProvider")
public class TestProvider {

    private @Setter Student student;

    @Inject
    @Named("testAssignBo")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudBusinessObject<TestAssign> assignBo;
    
    @Inject
    @Named("resultDao")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudDao<Result> resultDao;

    private TestAssign assignment;

    public List<TestAssign> getAssignes() {
        // TMP query. TODO: jpql for search by group
        List<TestAssign> res = new ArrayList<TestAssign>();

        for (TestAssign assignment : assignBo.findAll()) {
            if (assignment.getGroups().contains(student.getGroup())) {
                res.add(assignment);
            }
        }
        return res;
    }

    // ************ Testing ******************
    private @Getter List<Question> questions;
    private Answer[] answers;
    
    private int cursor = 0;

    public void start(TestAssign assignment) {
        this.assignment = assignment;
        questions = new ArrayList<>(assignment.getTest().getQuestions());
        Collections.sort(questions, new Comparator<Question>() {

            @Override
            public int compare(Question o1, Question o2) {
                return o1.getId().compareTo(o2.getId());
            }

        });
        answers = new Answer[questions.size()];
    }

    public void answer(Answer answer) {
        answers[cursor] = answer;
        next();
        if (cursor < 0) {
            done();
        }
    }

    public Question getQuestion(int cursor) {
        this.cursor = cursor;
        return getQuestion();
    }

    public Question getQuestion() {
        return questions.get(this.cursor);
    }

    private void next() {
        if (++cursor < questions.size()) {
            if (answers[cursor] == null) {
                return;
            } else {
                for (int i = 0; i < answers.length - cursor; i++) {
                    if (answers[i] == null) {
                        cursor = i;
                        return;
                    }
                }
                cursor--;
            }
        } else {
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == null) {
                    cursor = i;
                    return;
                }
            }
            cursor--;
        }
    }

    public void done() {
        Map<Question, Answer> result = new LinkedHashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            result.put(questions.get(i), answers[i]);
        }
        
        resultDao.add(new ResultBuilder(student, 0, result, assignment).buildResult());
    }
}
