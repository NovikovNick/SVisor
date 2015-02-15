package ru.nick.managedbean;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("navigator")
@Scope("session")
public class Navigator{// implements Serializable
	

	//private static final long serialVersionUID = 1L;
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_STUDENT = "ROLE_USER";
	private static final String ROLE_TEACHER = "ROLE_TEACHER";

	
	private String currentPage = "main";
	
	
	
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public boolean isAdmin(){
		return hasRole(ROLE_ADMIN);
	}
	public boolean isStudent(){
		return hasRole(ROLE_STUDENT);
	}
	public boolean isTeacher(){
		return hasRole(ROLE_TEACHER);
	}	
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return false;
		}
		for (GrantedAuthority item : authentication.getAuthorities()) {
			if (item.toString().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String navigateTo(String page) {
		currentPage = page;
		return page;
	}
	
	
}



