package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Group;
import ru.nick.model.TestAssign;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за назначения на тест
 * 
 * @author NovikovNick
 *
 */
@Named("testAssignDao")
public class TestAssignDaoImpl extends AbstractCrudDao<TestAssign> {

    @Inject
    @Named("groupDao")
    SimpleCrudDao<Group> groupDao;

    @Override
    public List<TestAssign> findAll() {
        return query("TestAssign.getAll");
    }

    @Override
    public TestAssign update(TestAssign entity) {
        TestAssign testAssign = getById(entity);

        fieldUpdateInCicle(testAssign, entity, "Title", "Description", "Date_start", "Date_end",
                "Passing_score", "Completion_time", "Attempts", "Author", "Test");

        testAssign
                .setGroups(updateChild(testAssign, entity.getGroups(), groupDao, "getTestAssign"));

        return merge(testAssign);
    }

    @Override
    protected Class<TestAssign> getGenericClass() {
        return TestAssign.class;
    }

    @Override
    protected String[] getUpdatableField() {
        throw new UnsupportedOperationException();
    }

}
