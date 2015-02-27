package ru.nick.security;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * Класс отвечает за авторизацию
 * @author NovikovNick
 *
 */
@ManagedBean(name = "auth")
@RequestScoped
public class AuthenticationSvisor {

	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	private String username = null;
	private String password = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String login() {

		try {

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
			Authentication authenticate = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entity", new Entity(getUsername(), getPassword()));
			
			return "login";
			
		} catch (AuthenticationException authenticationException) {
			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
		}
		return null;
	}

	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		return "logout";
	}

	public String cancel() {
		return null;
	}
}
