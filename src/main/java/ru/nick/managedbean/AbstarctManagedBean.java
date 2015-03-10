package ru.nick.managedbean;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.SelectEvent;

import ru.nick.bo.SimpleCrudBusinessObject;

/**
 * <p>
 * <b>Основной класс пакета</b>. Скелетная реализация JSF-bean'а.
 * 
 * <h4>CRUD-операции</h4>
 * <p>
 * Реализует работу с базой данных, благодаря параметрическому полиморфизму.
 * Потомку следует лишь переопределить {@link #getBo()}, чтобы пользоваться
 * всеми представленными ниже методами
 * <p>
 * <ul>
 * <li><b>C</b>reate: {@link #add()},
 * <li><b>R</b>ead: {@link #findAll()}, {@link #getAll()},
 * {@link #findById(long)}, {@link #refresh()}
 * <li><b>U</b>pdate: {@link #update(T)}
 * <li><b>D</b>elete: {@link #delete(T)}
 * </ul>
 * </p>
 * 
 * <h4>Функционал для работы с полями ввода</h4>
 * <p>
 * <ul>
 * <li>{@link #getGenericClass()}
 * <li>{@link #fillFields()}
 * <li>{@link #clearForm()}
 * <li>{@link #validInputText(FacesContext, UIComponent, Object)}
 * </ul>
 * </p>
 * 
 * @param <T>
 *            сущность из пакета {@link ru.nick.model}
 * 
 * @author NovikovNick
 */
public abstract class AbstarctManagedBean<T> {

    private static final String EMPTY_FORM_FIELD = "тест";
    private @Getter @Setter T entity;
    protected @Getter List<T> all;

   
    public String add() {

        getBo().add(entity);
        Messages.info("entity_add", null);
        refresh();        
        return null;
    }

    public List<T> findAll() {
        return getBo().findAll();
    }

    public T findById(long id) {
        return getBo().getById(id);
    }

    @PostConstruct
    protected void refresh() {// TODO:It is not correct to use exception in app
                              // logic...
        try {
            entity = getGenericClass().newInstance();
        } catch (InstantiationException | IllegalAccessException | SecurityException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            all = findAll();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Соxраняет изменения сущности в БД
     * 
     * @param entity
     *            типа Т
     * @return JSF-action
     */
    public String update(T entity) {
        getBo().update(entity);
        refresh();
        return null;
    }

    /**
     * Удаляет сущность из БД
     * 
     * @param entity
     *            типа Т
     * @return JSF-action
     */
    public String delete(T entity) {
        getBo().delete(entity);
        refresh();
        return null;
    }

    /**
     * <p>
     * Обязательный к переопределению метод, позволяющий корректно пользоваться
     * CRUD-операциями родительского класса {@link AbstarctManagedBean}
     * 
     * @return возвращает {@link SimpleCrudBusinessObject} параметризированный
     *         сущностью из пакета {@link ru.nick.model}
     */
    protected abstract SimpleCrudBusinessObject<T> getBo();

    // ================================END=======================================

    // ======================= JSF field support ==============================
    // ===============================START======================================
    /**
     * <p>
     * Метод заменяет логичное {@code T.class}, на рабочий вариант)
     * 
     * @return возвращает класс generic'а
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getGenericClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Если в классе потомке присутствуют поля отмеченные аннотацией
     * {@link FormField}, то иx значения будут переданы entity
     * 
     * @param entity
     *            типа Т
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    protected void fillFields(T entity) {
        try {
            for (Field managedBeanField : getFormFields()) {
                Field entityField;
                entityField = entity.getClass().getDeclaredField(managedBeanField.getName());
                entityField.setAccessible(true);
                managedBeanField.setAccessible(true);
                entityField.set(entity, managedBeanField.get(this));
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Затирает все пля с аннотацией {@link FormField}
     * 
     * @return
     */
    protected void clearForm() {

        for (Field field : getFormFields()) {
            field.setAccessible(true);
            try {
                Class<?> type = field.getType();

                if (type.isPrimitive()) {
                    field.set(this, 0);
                } else if (type == String.class) {
                    field.set(this, EMPTY_FORM_FIELD);
                } else {
                    field.set(this, null);
                }

            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Field> getFormFields() {
        List<Field> res = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(FormField.class)) {
                res.add(field);
            }
        }
        return res;
    }

    /**
     * Валидация поля на размер
     * 
     * @param context
     * @param component
     * @param value
     */
    public void validInputText(FacesContext context, UIComponent component, Object value) {

        String input = (String) value;

        if (input.equals("")) {
            Messages.throwsValidateException("validation_empty", null);
        }
        if (input.length() < 2) {
            Messages.throwsValidateException("validation_length_small", null);
        }
        if (input.length() > 100) {
            Messages.throwsValidateException("validation_length_large", null);
        }

    }

    // ================================END=======================================

    
    private static class Messages {

        private static final String RESOURCE_BUNDLE_NAME = "messages";

        public static FacesMessage getMessage(String bundleName, String resourceId, Object[] params) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            String appBundle = app.getMessageBundle();
            Locale locale = getLocale(context);
            ClassLoader loader = getClassLoader();
            String summary = getString(appBundle, bundleName, resourceId, locale, loader, params);
            if (summary == null) {
                summary = "???" + resourceId + "???";
            }
            String detail = getString(appBundle, bundleName, resourceId + "_detail", locale, loader,
                    params);

            return new FacesMessage(summary, detail);
        }

        private static String getString(String bundle1, String bundle2, String resourceId,
                Locale locale, ClassLoader loader, Object[] params) {

            String resource = null;
            ResourceBundle bundle;

            if (bundle1 != null) {
                bundle = ResourceBundle.getBundle(bundle1, locale, loader);
                if (bundle != null) {
                    try {
                        resource = bundle.getString(resourceId);
                    } catch (MissingResourceException e) {
                        /* NOP */
                    }
                }
            }
            if (resource == null) {
                bundle = ResourceBundle.getBundle(bundle2, locale, loader);
                if (bundle != null) {
                    try {
                        resource = bundle.getString(resourceId);
                    } catch (MissingResourceException e) {
                        /* NOP */
                    }
                }
            }

            if (resource == null)
                return null; // mismatch
            if (params == null)
                return resource;

            return new MessageFormat(resource, locale).format(params);
        }

        private static ClassLoader getClassLoader() {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null)
                loader = ClassLoader.getSystemClassLoader();
            return loader;
        }

        private static Locale getLocale(FacesContext context) {

            Locale locale = null;
            UIViewRoot viewRoot = context.getViewRoot();
            if (viewRoot != null) {
                locale = viewRoot.getLocale();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            return locale;
        }

        public static void throwsValidateException(String resourceId, Object[] params) {
            FacesMessage msg = getMessage(RESOURCE_BUNDLE_NAME, resourceId, params);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        public static void info(String resourceId, Object[] params) {
            FacesMessage msg = getMessage(RESOURCE_BUNDLE_NAME, resourceId, params);
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
    }
}
