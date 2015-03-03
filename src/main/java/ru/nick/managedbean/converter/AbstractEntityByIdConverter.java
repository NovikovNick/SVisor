package ru.nick.managedbean.converter;

import java.lang.reflect.ParameterizedType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.nick.model.Identifiable;

/**
 * <b>Основной класс пакета</b>. Скелетная реализация JSF-конвертера.
 * 
 * @author NovikovNick
 *
 * @param <T>
 */
public abstract class AbstractEntityByIdConverter<T extends Identifiable> implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        T entity = null;
        try {
            entity = getGenericClass().newInstance();
            entity.setId(new Long(value));
        } catch (InstantiationException | IllegalAccessException | SecurityException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return entity;
    };

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String r = "";
        if (value instanceof Identifiable) {
            Identifiable entity = (Identifiable) value;
            r = entity.getId() + "";
        } else if (value instanceof String) {
            r = (String) value;
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getGenericClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
