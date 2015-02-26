package ru.nick.managedbean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * <p>
 * Эта аннотация указывает на то, что поле присутствует на странице .xhtml на форме, и 
 * будет неявно обработано в контексте логики данного уровня посредством {@link AbstarctManagedBean}
 * 
 * @author NovikovNick
 */
@Target(value=ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface FormField {

}
