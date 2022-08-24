package com.mustafa.trial.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commands {

	@Id
	private String commandName;
	
	private String className;
	
	private String methodName;
	
	
	public Commands() {
		
	}


	public Commands(String commandName, String className, String methodName) {
		super();
		this.commandName = commandName;
		this.className = className;
		this.methodName = methodName;
	}


	public String getCommandName() {
		return commandName;
	}


	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	

	
}
