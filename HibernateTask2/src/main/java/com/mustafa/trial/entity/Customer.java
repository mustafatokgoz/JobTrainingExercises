package com.mustafa.trial.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;


@Entity
public class Customer extends BaseOperations{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	String name;
	
	String surname;
	
	String email;
	
	
	public Customer() {
		
	}

	public Customer(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		
		return String.format("Id: %d Name: %s, Surname: %s, Email: %s",this.id,this.name,this.surname,this.email);
	}
	
	
	public void list(Session session) {
		 List<?> customers = session.createQuery("from Customer").list();
	        for(Object acustumer: customers) {
	        	Customer acust = (Customer) acustumer;
	        	System.out.println(acust.toString());
	        			
	        }
	}
	

}
