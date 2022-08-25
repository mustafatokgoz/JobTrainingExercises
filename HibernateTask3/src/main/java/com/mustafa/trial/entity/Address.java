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
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String address;
	
	
	
	@OneToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "id")
	private Customer cust_id;
	
	public Address() {

	}

	public Address(String address,Customer cust) {
		
		this.address = address;
		this.cust_id = cust;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", cust_id=" + cust_id + "]";
	}

	
	public void insert(Session session,XBag bag) {
		address = (String) bag.getMap().get(BagKey.ADDRESS_ADDRESS);
		cust_id = getCustomer(session,bag);
		session.save(this);
	}
	
	public void update(Session session,XBag bag) {
		Address updated1 = this.get(session,bag);
		updated1.setAddress((String) bag.getMap().get(BagKey.ADDRESS_ADDRESS));
		updated1.setCust(getCustomer(session,bag));
		session.update(updated1);
	}
	
	public void delete(Session session,XBag bag) {
		Address deleted = this.get(session,bag);
		session.delete(deleted);
	}
	
	public Customer getCustomer(Session session, XBag bag) {
		if(bag.getMap().get(BagKey.CUSTOMER_ID) != null) {
			return (Customer) session.load(Customer.class, (Serializable) bag.getMap().get(BagKey.CUSTOMER_ID));
		}
		return null;
	}
	
	public Address get(Session session, XBag bag) {
		if(bag.getMap().get(BagKey.ADDRESS_ID)!= null) {
			return (Address) session.load(Phone.class, (Serializable) bag.getMap().get(BagKey.ADDRESS_ID));
		}
		return null;
	}
	
	
	public void list(Session session) {
		 List<?> addresses = session.createQuery("from Address").list();
	        for(Object aaddress: addresses) {
	        	Address aaddr = (Address) aaddress;
	        	System.out.println(aaddr.toString());
	        			
	        }
	}

}
