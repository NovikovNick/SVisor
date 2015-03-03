package ru.nick.bo;

import java.util.List;

import ru.nick.model.Discipline;
import ru.nick.model.Group;
import ru.nick.model.Teacher;

/**
 * Интерфейс бизнес слоя для преподавателя
 * 
 * @author NovikovNick
 *
 */
public interface TeacherBo extends SimpleCrudBusinessObject<Teacher> {

    public List<Discipline> getDisciplineList(Teacher teacher);

    public List<Group> getGroupList(Teacher teacher);

    public List<Discipline> getAllDisciplines(Teacher teacher);

    public List<Group> getAllGroups(Teacher teacher);

}
