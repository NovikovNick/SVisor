package ru.nick.managedbean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * <p>Эта аннотация указывает на то, что поле является отображением 
 * поля на форме. Применяется исключительно к полю. 
 * <hr>
 * <p>This annotation indicates that the field is a mapping
 * field on the form. Applied exclusively to the field
 * 
 * @author NovikovNick
 */
@Target(value=ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface FormField {

}
