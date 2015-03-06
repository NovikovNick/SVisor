package ru.nick.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.impl.TestProvider;
import ru.nick.dao.EntityDao;
import ru.nick.model.Answer;
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
    public @Getter(AccessLevel.PROTECTED) EntityDao<Student> dao;
    
    @Inject
    @Named("testProvider")
    private @Getter @Setter TestProvider test;

    @PostConstruct
    private void init() {
        test.setStudent(getEntity());
    }
    
    
    // *********** Getter/setter methods ****************//
    // ************* START ********************//
    public List<TestAssign> getAssignes() {
        return test.getAssignes();
    }
    // ************* END ********************//

    public String start(TestAssign assign) {
        return null;
    }

    public String done() {
        return null;
    }

    public boolean isActiveAssign() {
        return false;
    }

    public void next(Answer answer) {

    }

}
