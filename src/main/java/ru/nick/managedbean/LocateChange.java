package ru.nick.managedbean;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
/**
 * Смена языковой локали
 * @author NovikovNick
 *
 */
@Named
@Scope("session")
public class LocateChange {

	public String en(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);
		return null;
	}
	
	public String rus(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("ru", ""));
		return null;
	}
}
