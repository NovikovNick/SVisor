package ru.nick.managedbean;

import java.util.List;

import ru.nick.dao.SimpleCrudDao;

public abstract class AbstarctManagedBean<T> {

//	/** Create */
//	public void add() {
//		Discipline discipline = new Discipline();
//		discipline.setTitle(getTitle());
//		dao.add(discipline);
//		clearForm();
//	}

	/** Read */
	public List<T> findAll() {
		return getDao().findAll();
	}
	/** Update */
	public String update(T entity) {
		getDao().update(entity);
		return null;
	}
	/** Delete */
	public String delete(T entity) {
		getDao().delete(entity);
		return null;
	}
	// ************* END ********************//
	
	protected abstract SimpleCrudDao<T> getDao();
	protected abstract void refresh();
	
}
