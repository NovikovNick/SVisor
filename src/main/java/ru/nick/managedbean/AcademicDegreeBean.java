package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.model.AcademicDegree;

/**
 * Класс-наследник {@link AbstarctManagedBean}. Отвечает за ученую степень
 * 
 * @author NovikovNick
 */
@Component("academicDegreeBean")
@Scope("request")
public class AcademicDegreeBean extends AbstarctManagedBean<AcademicDegree> {

    @Inject
    @Named("academicDegreeBo")
    @Getter(AccessLevel.PROTECTED)
    private SimpleCrudBusinessObject<AcademicDegree> bo;

    @FormField
    @Getter
    @Setter
    private String fullDegree;

    @FormField
    @Getter
    @Setter
    private String reducDegree;

}
