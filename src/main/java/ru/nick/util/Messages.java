package ru.nick.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class Messages {

	private static final String RESOURCE_BUNDLE_NAME = "messages";

	public static FacesMessage getMessage(String bundleName, String resourceId, Object[] params ){
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		String summary = getString(appBundle, bundleName, resourceId, locale, loader, params);
		if (summary == null) {
			summary = "???" + resourceId + "???";
		}
		String detail = getString(appBundle, bundleName, resourceId+"_detail", locale, loader, params);
		
		return new FacesMessage(summary, detail);
	}

	private static String getString(String bundle1, String bundle2,
			String resourceId, Locale locale, ClassLoader loader,
			Object[] params) {
		
		String resource = null;
		ResourceBundle bundle;
		
		if (bundle1 != null) {
			bundle = ResourceBundle.getBundle(bundle1, locale, loader);
			if (bundle != null) {
				try {
					resource = bundle.getString(resourceId);
				} catch (MissingResourceException e) {/*NOP*/}
			}
		}
		if (resource == null) {
			bundle = ResourceBundle.getBundle(bundle2, locale, loader);
			if (bundle != null) {
				try {
					resource = bundle.getString(resourceId);
				} catch (MissingResourceException e) {/*NOP*/}
			}
		}
		
		if (resource == null) return null; //mismatch
		if (params == null) return resource;
		
		return new MessageFormat(resource, locale).format(params);
	}

	
	private static ClassLoader getClassLoader() {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) loader = ClassLoader.getSystemClassLoader();
		return loader;
		
	}

	private static Locale getLocale(FacesContext context) {
		
		Locale locale = null;
		UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null) locale = viewRoot.getLocale();
		if (locale == null) locale = Locale.getDefault();
		return locale;
		
	}
	
	public static void throwsValidateException(String resourceId, Object[] params) {
		FacesMessage msg = getMessage(RESOURCE_BUNDLE_NAME, resourceId, params);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);
	}
}
