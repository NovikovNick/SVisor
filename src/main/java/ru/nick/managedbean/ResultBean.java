package ru.nick.managedbean;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.bo.testing.ResultBuilder;
import ru.nick.model.Result;

/**
 * Класс-наследник {@link AbstarctManagedBean}. Отвечает за результаты
 * тестирования
 * 
 * @author NovikovNick
 *
 */
@Component("resultBean")
@Scope("request")
public class ResultBean extends AbstarctManagedBean<Result> {

    @Override
    protected SimpleCrudBusinessObject<Result> getBo() {
       throw new UnsupportedOperationException();
    }

//    @Inject
//    @Named("resultParser")
//    @Getter(AccessLevel.PROTECTED)
//    private ResultParser bo;

}
