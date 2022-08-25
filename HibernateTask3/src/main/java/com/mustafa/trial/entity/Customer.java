package com.mustafa.trial.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;

import com.mustafa.trial.bags.*;


@Entity
public class Customer{
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
	
	public void insert(Session session,XBag bag) {
		name = (String) bag.getMap().get(BagKey.CUSTOMER_NAME);
		surname = (String) bag.getMap().get(BagKey.CUSTOMER_SURNAME);
		email = (String) bag.getMap().get(BagKey.CUSTOMER_EMAIL);
		session.save(this);
	}
	
	public void update(Session session,XBag bag) {
		Customer updated1 = this.get(session,bag);
		updated1.setName( (String) bag.getMap().get(BagKey.CUSTOMER_NAME));
		updated1.setSurname( (String) bag.getMap().get(BagKey.CUSTOMER_SURNAME));
		updated1.setEmail((String) bag.getMap().get(BagKey.CUSTOMER_EMAIL));
		session.update(updated1);
	}
	
	public void delete(Session session,XBag bag) {
		Customer deleted = this.get(session,bag);
		session.delete(deleted);
	}
	
	public void list(Session session,XBag bag) {
		 List<?> customers = session.createQuery("from Customer").list();
	        for(Object acustumer: customers) {
	        	Customer acust = (Customer) acustumer;
	        	System.out.println(acust.toString());
	        			
	        }
	}
	
	public Customer get(Session session, XBag bag) {
		if(bag.getMap().get(BagKey.CUSTOMER_ID) != null) {
			return (Customer) session.load(Customer.class, (Serializable) bag.getMap().get(BagKey.CUSTOMER_ID));
		}
		return null;
	}
	

}
