package com.mustafa.trial.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.Session;

import com.mustafa.trial.bags.BagKey;
import com.mustafa.trial.bags.XBag;


@Entity
public class Phone{
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
	
	public void insert(Session session,XBag bag) {
		phone_number = (String) bag.getMap().get(BagKey.PHONE_NUMBER);
		cust_id = getCustomer(session,bag);
		session.save(this);
	}
	
	public void update(Session session,XBag bag) {
		Phone updated1 = this.get(session,bag);
		updated1.setPhone_number((String) bag.getMap().get(BagKey.PHONE_NUMBER));
		updated1.setCust(getCustomer(session,bag));
		session.update(updated1);
	}
	
	public void delete(Session session,XBag bag) {
		Phone deleted = this.get(session,bag);
		session.delete(deleted);
	}
	
	public Customer getCustomer(Session session, XBag bag) {
		if(bag.getMap().get(BagKey.CUSTOMER_ID) != null) {
			return (Customer) session.load(Customer.class, (Serializable) bag.getMap().get(BagKey.CUSTOMER_ID));
		}
		return null;
	}
	
	public Phone get(Session session, XBag bag) {
		if(bag.getMap().get(BagKey.PHONE_ID)!= null) {
			return (Phone) session.load(Phone.class, (Serializable) bag.getMap().get(BagKey.PHONE_ID));
		}
		return null;
	}
	
	
	public void list(Session session) {
		 List<?> phones = session.createQuery("from Phone").list();
	        for(Object aphone: phones) {
	        	Customer phone = (Customer) aphone;
	        	System.out.println(phone.toString());
	        			
	        }
	}
	

}
