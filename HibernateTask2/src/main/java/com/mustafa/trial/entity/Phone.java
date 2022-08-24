package com.mustafa.trial.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.Session;


@Entity
public class Phone extends BaseOperations{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String phone_number;
	

	@OneToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "id")
	private Customer cust_id;
	
	public Phone() {
		
	}
	
	
	public Phone(String phone_number, Customer cust_id) {
		super();
		this.phone_number = phone_number;
		this.cust_id = cust_id;
	}

	public Customer getCust() {
		return cust_id;
	}

	public void setCust(Customer cust) {
		this.cust_id = cust;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public Customer getCust_id() {
		return cust_id;
	}


	public void setCust_id(Customer cust_id) {
		this.cust_id = cust_id;
	}
	
	public void insert(Session session) {
		session.save(this);
	}
	
	public void update(Session session) {
		session.update(this);
	}
	
	public void delete(Session session) {
		session.delete(this);
	}
	
	public void list(Session session) {
		 List<?> phones = session.createQuery("from Phone").list();
	        for(Object aphone: phones) {
	        	Customer phone = (Customer) aphone;
	        	System.out.println(phone.toString());
	        			
	        }
	}
	

}
