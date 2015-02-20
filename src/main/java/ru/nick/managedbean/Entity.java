package ru.nick.managedbean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private @Getter @Setter Long id;
	@NonNull private @Getter @Setter String login;
	@NonNull private @Getter @Setter String password;
	
}
