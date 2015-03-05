package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import ru.nick.bo.ResultBo;
import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.Answer;
import ru.nick.model.Question;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

@Named("testProvider")
public class TestProvider {

    private @Getter @Setter Student student;

    @Inject
    @Named("testAssignBo")
    public SimpleCrudBusinessObject<TestAssign> assignBo;

    @Inject
    @Named("resultBo")
    private ResultBo resultBo;

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

    private List<Question> questions;
    private Answer[] answers;

    private int cursor = 0;

    public void start(TestAssign assign) {
        questions = new ArrayList<>(assign.getTest().getQuestions());
        answers = new Answer[questions.size()];
    }

    public void answer(Answer answer) {
        answers[cursor] = answer;
        next();
        if (cursor < 0) {
            done();
        }
    }

    public Question getQuestion(){
        return questions.get(cursor);
    }

    private void next(){
        cursor++;
        if (cursor < questions.size()) {
            if (answers[cursor] == null) {
                return;
            }
            else {
                for (int i = 0; i < (questions.size() - cursor); i++) {
                    if(answers[cursor+i] == null){
                        cursor += i;
                        return;
                    }
                }
                for (int i = 0; i < cursor; i++) {
                    if(answers[cursor-i] == null){
                        cursor -= i;
                        return;
                    }
                }
            }
        }
    }
    
    private void done() {
        System.out.println("done");
    }
}
