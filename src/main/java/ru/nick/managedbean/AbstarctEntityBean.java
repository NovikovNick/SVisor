package ru.nick.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import lombok.Getter;
import ru.nick.dao.EntityDao;
import ru.nick.security.Entity;

abstract public class AbstarctEntityBean<T> {

    private @Getter T entity;
    abstract protected EntityDao<T> getDao();
    
    @PostConstruct
    private void refresh() {
        Entity entity = (Entity) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("entity");
        this.entity = getDao().getByLoginPassword(entity.getLogin(), entity.getPassword());
    }
}
