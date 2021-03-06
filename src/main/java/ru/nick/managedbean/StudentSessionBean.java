package ru.nick.managedbean;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.testing.TestProvider;
import ru.nick.dao.EntityDao;
import ru.nick.model.Question;
import ru.nick.model.Student;
import ru.nick.model.TestAssign;

/**
 * Сессионный bean поддержки действий студента
 * 
 * @author NovikovNick
 *
 */
@Component("student")
@Scope("session")
public class StudentSessionBean extends AbstarctEntityBean<Student> {

    @Inject
    @Named("studentDao")
    @Getter(AccessLevel.PROTECTED)
    public EntityDao<Student> dao;

    @Inject
    @Named("testProvider")
    private @Getter @Setter TestProvider test;

    
    private @Getter @Setter boolean activeAssign;
    
    @PostConstruct
    private void init() {
        test.setStudent(getEntity());
    }

    
    
    public String start(TestAssign assign) {
        test.start(assign);
        activeAssign = true;
        return null;
    }

    public String getQuestion(int cursor) {
        test.getQuestion(cursor);
        return null;
    }
    
    public Question getQuestion() {
        return test.getQuestion();
    }
    
    public String done() {
        test.done();
        activeAssign = false;
        return null;
    }


}
